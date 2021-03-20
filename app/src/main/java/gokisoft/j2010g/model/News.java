package gokisoft.j2010g.model;

import android.icu.text.SimpleDateFormat;
import android.util.Log;

import java.util.Date;

/**
 * Created by Diep.Tran on 3/17/21.
 */

public class News {
    String thumbnail, title, description, createdAt;
    int _id;

    public News() {
    }

    public News(String thumbnail, String title, String description, String createdAt) {
        this.thumbnail = thumbnail;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
    }

    public News(int _id, String thumbnail, String title, String description, String createdAt) {
        this.thumbnail = thumbnail;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this._id = _id;
    }

    public News(String thumbnail, String title, String description) {
        this.thumbnail = thumbnail;
        this.title = title;
        this.description = description;

        SimpleDateFormat format = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            format = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
            createdAt = format.format(new Date());
            Log.d(News.class.getName(), createdAt);
            Log.d(News.class.getName(), "yes");
        }
        Log.d(News.class.getName(), "Finish");
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
}
