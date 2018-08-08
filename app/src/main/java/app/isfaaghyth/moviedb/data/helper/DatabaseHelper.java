package app.isfaaghyth.moviedb.data.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import app.isfaaghyth.moviedb.data.local.DatabaseConstruct;

/**
 * Created by isfaaghyth on 7/30/18.
 * github: @isfaaghyth
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context){
        super(context, DatabaseConstruct.DB_NAME, null, DatabaseConstruct.DB_VERSION);
    }

    @Override public void onCreate(SQLiteDatabase db) {
        db.execSQL(doCreateDatabase());
    }

    @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(dropDatabase());
        onCreate(db);
    }

    private String doCreateDatabase() {
        return DatabaseConstruct.createTable();
    }

    private String dropDatabase() {
        return DatabaseConstruct.dropTable();
    }

}