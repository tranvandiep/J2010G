package gokisoft.j2010g.entity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import gokisoft.j2010g.db.DBHelper;
import gokisoft.j2010g.model.News;

/**
 * Created by Diep.Tran on 3/20/21.
 */

/**
 * CRUD: FINISH - ORM Greeen & DB Flow -> Library (sqlite)
 */
public class NewsModify {
    public static final String SQL_CREATE_TABLE = "create table news (\n" +
            "\t_id integer primary key autoincrement,\n" +
            "\ttitle varchar(200),\n" +
            "\tthumbnail varchar(200),\n" +
            "\tdescription text,\n" +
            "\tcreated_at varchar(20)\n" +
            ")";


    public static void insert(News news) {
        SQLiteDatabase sqLiteDatabase = DBHelper.getInstance(null).getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("title", news.getTitle());
        values.put("thumbnail", news.getThumbnail());
        values.put("description", news.getDescription());
        values.put("created_at", news.getCreatedAt());

        sqLiteDatabase.insert("news", null, values);

        Log.d(NewsModify.class.getName(), "insert success");
    }

    public static void update(News news) {
        SQLiteDatabase sqLiteDatabase = DBHelper.getInstance(null).getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("title", news.getTitle());
        values.put("thumbnail", news.getThumbnail());
        values.put("description", news.getDescription());
        values.put("created_at", news.getCreatedAt());

        sqLiteDatabase.update("news", values, "_id = " + news.get_id(), null);
//        sqLiteDatabase.update("news", values, "_id = ?", new String[] {String.valueOf(news.get_id())});
    }

    public static void delete(int _id) {
        SQLiteDatabase sqLiteDatabase = DBHelper.getInstance(null).getWritableDatabase();

        sqLiteDatabase.delete("news", "_id = " + _id, null);
    }

    public static List<News> getNewsList() {
        List<News> newsList = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = DBHelper.getInstance(null).getWritableDatabase();

        String sql = "select * from news";

        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

        while(cursor.moveToNext()) {
            News news = new News(cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getString(cursor.getColumnIndex("thumbnail")),
                    cursor.getString(cursor.getColumnIndex("title")),
                    cursor.getString(cursor.getColumnIndex("description")),
                    cursor.getString(cursor.getColumnIndex("created_at")));

            newsList.add(news);
        }

        return newsList;
    }
}
