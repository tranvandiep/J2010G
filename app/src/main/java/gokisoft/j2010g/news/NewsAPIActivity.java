package gokisoft.j2010g.news;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import gokisoft.j2010g.CounterService;
import gokisoft.j2010g.R;
import gokisoft.j2010g.model.News;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewsAPIActivity extends AppCompatActivity {
    static final String API_NEWS_LIST = "https://raw.githubusercontent.com/tranvandiep/J2010G/master/api/news.json";

    ListView listView;

    List<News> dataList = new ArrayList<>();

    NewsAdapter adapter;

    TextView txtCounter;

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //Noi du lieu duoc gui toi
            String action = intent.getAction();
            if(action.equals("ADD_NEWS")) {
                //Du lieu -> them vao quan ly tin tuc
                String title = intent.getStringExtra("title");
                Log.d(NewsAPIActivity.class.getName(), title);
                String thumbnail = intent.getStringExtra("thumbnail");
                Log.d(NewsAPIActivity.class.getName(), thumbnail);
                String content = intent.getStringExtra("content");
                Log.d(NewsAPIActivity.class.getName(), content);
                String created_at = intent.getStringExtra("created_at");
                Log.d(NewsAPIActivity.class.getName(), created_at);

                News news = new News(thumbnail, title, content, created_at);
                dataList.add(news);

                adapter.notifyDataSetChanged();
            } else if(action.equals("ACTION_COUNTER")) {
                final int count = intent.getIntExtra("counter", 0);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        txtCounter.setText(String.valueOf(count));
                    }
                });
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        listView = findViewById(R.id.an_listview);
        txtCounter = findViewById(R.id.an_counter);

        //Adapter
        adapter = new NewsAdapter(this, dataList);

        listView.setAdapter(adapter);

        getAPI(API_NEWS_LIST);

        //Dang ky de receiver hoat dong -> lang nghe dc action can.
        IntentFilter filter = new IntentFilter();
        filter.addAction("ADD_NEWS");
        filter.addAction("ACTION_COUNTER");

        try {
            registerReceiver(receiver, filter);
        } catch(Exception e) {}
    }

    @Override
    public void finish() {
        unregisterReceiver(receiver);

        super.finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_news_app, menu);

        return super.onCreateOptionsMenu(menu);
    }

    Intent service = null;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mna_add_news:
                //Them News moi
                Intent i = new Intent(NewsAPIActivity.this, EditorNewsActivity.class);
                startActivity(i);
                break;
            case R.id.mna_counter:
                service = new Intent(NewsAPIActivity.this, CounterService.class);
                startService(service);
                break;
            case R.id.mna_stop_counter:
                if(service != null) {
                    stopService(service);
                    service = null;
                }
                break;
            case R.id.mna_exit:
                //Thoat chuong trinh
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * https://www.vogella.com/tutorials/JavaLibrary-OkHttp/article.html
     * @param url
     */
    void getAPI(String url) {
        // avoid creating several instances, should be singleon
        OkHttpClient client = new OkHttpClient();


        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                } else {
                    // do something wih the result
                    String json = response.body().string();
                    Log.d(NewsAPIActivity.class.getName(), json);
                    parseJSON(json);
                }
            }
        });
    }

    void parseJSON(String content) {
        Gson gson = new Gson();

        Type collectionType = new TypeToken<List<News>>(){}.getType();

        List<News> list = gson.fromJson(content, collectionType);

        for (News news : list) {
            Log.d(NewsAPIActivity.class.getName(), news.toString());
            dataList.add(news);
        }

        adapter.notifyDataSetChanged();
    }
}
