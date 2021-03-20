package gokisoft.j2010g.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import gokisoft.j2010g.entity.NewsModify;

/**
 * Created by Diep.Tran on 3/20/21.
 */

public class DBHelper extends SQLiteOpenHelper{
    private static final String DB_NAME = "J2010G";
    private static final int DB_VERSION = 1;

    private static DBHelper instance = null;

    private DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public synchronized static DBHelper getInstance(Context context) {
        Log.d(DBHelper.class.getName(), "getInstance init");

        if(instance == null) {
            Log.d(DBHelper.class.getName(), "getInstance");
            instance = new DBHelper(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Goi duy mot lan khi chung ta cai dat App (database)
        Log.d(DBHelper.class.getName(), "onCreate Start");

        sqLiteDatabase.execSQL(NewsModify.SQL_CREATE_TABLE);

        Log.d(DBHelper.class.getName(), "onCreate End");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        //Moi lan nang cap version database -> goi lai vao day
    }
}
