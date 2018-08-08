package app.isfaaghyth.moviedb.data.local;

import android.database.Cursor;
import android.net.Uri;

/**
 * Created by isfaaghyth on 8/7/18.
 * github: @isfaaghyth
 */

public class DatabaseConstruct {

    public static final String DB_NAME = "movieghyth";
    public static final String TABLE_FAVORITES = "favorites";

    static final int DB_VERSION = 1;

    public static final String CONTENT_AUTHORITY = "app.isfaaghyth.moviedb";

    public static final Uri CONTENT_URI = new Uri.Builder().scheme("content")
            .authority(CONTENT_AUTHORITY)
            .appendPath(TABLE_FAVORITES)
            .build();


    public static String createTable() {
        return "CREATE TABLE " + TABLE_FAVORITES + " (" +
                FavoritesColumn._ID + " INTEGER PRIMARY KEY," +
                FavoritesColumn.getVote_average() + " TEXT," +
                FavoritesColumn.getVote_count() + " TEXT," +
                FavoritesColumn.getTitle() + " TEXT," +
                FavoritesColumn.getPoster_path() + " TEXT," +
                FavoritesColumn.getBackdrop_path() + " TEXT," +
                FavoritesColumn.getOverview() + " TEXT," +
                FavoritesColumn.getRelease_date() + " TEXT)";
    }

    public static String dropTable() {
        return "DROP TABLE IF EXISTS " + TABLE_FAVORITES;
    }

//    public static String insert() {
//        return "INSERT INTO " + TABLE_FAVORITES + " (" +
//                FavoritesColumn.getVote_average() + "," +
//                FavoritesColumn.getVote_count() + "," +
//                FavoritesColumn.getTitle() + "," +
//                FavoritesColumn.getPoster_path() + "," +
//                FavoritesColumn.getBackdrop_path() + "," +
//                FavoritesColumn.getOverview() + "," +
//                FavoritesColumn.getRelease_date() + ") VALUES (?, ?)";
//    }
//
//    public static String getAll() {
//        return "SELECT * FROM "+ TABLE_FAVORITES +" ORDER BY " +
//                FavoritesColumn.getTitle() + " ASC";
//    }

    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }

    public static long getColumnLong(Cursor cursor, String columnName) {
        return cursor.getLong(cursor.getColumnIndex(columnName));
    }

}
