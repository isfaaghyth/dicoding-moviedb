package app.isfaaghyth.moviedb.data.local;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by isfaaghyth on 8/8/18.
 * github: @isfaaghyth
 */

public class FavoriteManager {

    private Context context;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public FavoriteManager(Context context) {
        this.context = context;
    }

    public FavoriteManager open() throws SQLException {
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        databaseHelper.close();
    }

    
}
