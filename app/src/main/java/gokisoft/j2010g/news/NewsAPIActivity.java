package gokisoft.j2010g.news;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import gokisoft.j2010g.R;
import gokisoft.j2010g.model.News;

public class NewsAPIActivity extends AppCompatActivity {
    ListView listView;

    List<News> dataList = new ArrayList<>();

    NewsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        listView = findViewById(R.id.an_listview);

        //Adapter
        adapter = new NewsAdapter(this, dataList);

        listView.setAdapter(adapter);
    }
}
