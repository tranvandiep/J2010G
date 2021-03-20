package gokisoft.j2010g.news;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import gokisoft.j2010g.R;
import gokisoft.j2010g.model.News;

/**
 * Created by Diep.Tran on 3/17/21.
 */

public class NewsAdapter extends BaseAdapter {
    Activity activity;
    List<News> dataList;

    public NewsAdapter(Activity activity, List<News> dataList) {
        this.activity = activity;
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int i) {
        return dataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int index, View view, ViewGroup viewGroup) {
        //Load Item List View
        view = activity.getLayoutInflater().inflate(R.layout.item_news, null);

        News news = dataList.get(index);
        ImageView imageView = view.findViewById(R.id.in_thumbnail);
        TextView txtTitle = view.findViewById(R.id.in_title);
        TextView txtDes = view.findViewById(R.id.in_description);
        TextView txtCreatedAt = view.findViewById(R.id.in_created_at);

        //Hien thi hinh anh len imageView
        Log.d(NewsAdapter.class.getName(), news.getThumbnail());
        Picasso.get().load(news.getThumbnail()).into(imageView);

        txtTitle.setText(news.getTitle());
        txtDes.setText(news.getDescription());
        txtCreatedAt.setText(news.getCreatedAt());

        return view;
    }
}
