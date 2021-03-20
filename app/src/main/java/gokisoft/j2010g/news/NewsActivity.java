package gokisoft.j2010g.news;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import gokisoft.j2010g.R;
import gokisoft.j2010g.db.DBHelper;
import gokisoft.j2010g.entity.NewsModify;
import gokisoft.j2010g.model.News;

public class NewsActivity extends AppCompatActivity {
    ListView listView;

    List<News> dataList = new ArrayList<>();

    NewsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        DBHelper.getInstance(this);

        listView = findViewById(R.id.an_listview);

        //Fake data
        dataList = NewsModify.getNewsList();
//        dataList.add(new News("https://photo-baomoi.zadn.vn/w700_r16x9/2021_03_17_293_38243197/aac55c2e6a6c8332da7d.jpg", "Tieu de 1", "Noi dung 1", "2021-03-17 10:00"));
//        dataList.add(new News("https://photo-baomoi.zadn.vn/w200_r3x2/2021_03_17_119_38243500/8ff327171155f80ba144.jpg", "Tieu de 2", "Noi dung 2", "2021-03-17 10:00"));
//        dataList.add(new News("https://photo-baomoi.zadn.vn/w200_r3x2/2021_03_17_114_38243286/912b37a80deae4b4bdfb.jpg", "Tieu de 3", "Noi dung 3", "2021-03-17 10:00"));
//        dataList.add(new News("https://photo-baomoi.zadn.vn/w200_r3x2/2021_03_17_20_38243676/77131ef428b6c1e898a7.jpg", "Tieu de 4", "Noi dung 4", "2021-03-17 10:00"));
//        dataList.add(new News("https://photo-baomoi.zadn.vn/w600_r3x2/2021_03_17_23_38243079/43c8fe20c862213c7873.jpg.webp", "Tieu de 5", "Noi dung 5", "2021-03-17 10:00"));

        //Adapter
        adapter = new NewsAdapter(this, dataList);

        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_news_app, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mna_add_news:
                //Them News moi
                showNewsDialog();
                break;
            case R.id.mna_exit:
                //Thoat chuong trinh
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    void showNewsDialog() {
        View v = LayoutInflater.from(this).inflate(R.layout.dialog_news, null);

        final EditText txtTitle = v.findViewById(R.id.dn_title);
        final EditText txtThumbnail = v.findViewById(R.id.dn_thumbnail);
        final EditText txtDes = v.findViewById(R.id.dn_description);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(v);

        builder.setPositiveButton("Save News", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(NewsActivity.this, "Cancel", Toast.LENGTH_SHORT).show();

                String title = txtTitle.getText().toString();
                String thumbnail = txtThumbnail.getText().toString();
                String description = txtDes.getText().toString();

                News news = new News(thumbnail, title, description, "2021-03-20");

                dataList.add(news);

                adapter.notifyDataSetChanged();

                NewsModify.insert(news);
            }
        }).setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(NewsActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
            }
        }).show();
    }
}
