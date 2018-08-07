package app.isfaaghyth.moviedb.data.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import app.isfaaghyth.moviedb.data.Movie;

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

    public List<Movie> query() {
        List<Movie> movies = new ArrayList<>();
        Cursor cursor = database.query(DatabaseConstruct.DB_NAME,null,null,null,null,null, FavoritesColumn._ID + " DESC",null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                Movie movie = new Movie();
                movie.setId(cursor.getInt(cursor.getColumnIndexOrThrow(FavoritesColumn._ID)));
                movie.setVote_average(cursor.getFloat(cursor.getColumnIndexOrThrow(FavoritesColumn.getVote_average())));
                movie.setVote_count(cursor.getInt(cursor.getColumnIndexOrThrow(FavoritesColumn.getVote_count())));
                movie.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(FavoritesColumn.getTitle())));
                movie.setPoster_path(cursor.getString(cursor.getColumnIndexOrThrow(FavoritesColumn.getPoster_path())));
                movie.setBackdrop_path(cursor.getString(cursor.getColumnIndexOrThrow(FavoritesColumn.getBackdrop_path())));
                movie.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(FavoritesColumn.getOverview())));
                movie.setRelease_date(cursor.getString(cursor.getColumnIndexOrThrow(FavoritesColumn.getRelease_date())));
                movies.add(movie);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return movies;
    }

    public long insert(Movie movie) {
        ContentValues values = new ContentValues();
        values.put(FavoritesColumn.getVote_average(), movie.getVote_average());
        values.put(FavoritesColumn.getVote_count(), movie.getVote_count());
        values.put(FavoritesColumn.getTitle(), movie.getTitle());
        values.put(FavoritesColumn.getPoster_path(), movie.getPoster_path());
        values.put(FavoritesColumn.getBackdrop_path(), movie.getBackdrop_path());
        values.put(FavoritesColumn.getOverview(), movie.getOverview());
        values.put(FavoritesColumn.getRelease_date(), movie.getRelease_date());
        return database.insert(DatabaseConstruct.DB_NAME, null, values);
    }

    public int delete(int id) {
        return database.delete(DatabaseConstruct.DB_NAME, FavoritesColumn._ID + "='"+id+"'", null);
    }

    public Cursor queryByIdProvider(String id){
        return database.query(DatabaseConstruct.DB_NAME, null
                ,FavoritesColumn._ID + " = ?"
                ,new String[]{id}
                ,null
                ,null
                ,null
                ,null);
    }

    public Cursor queryProvider(){
        return database.query(DatabaseConstruct.DB_NAME
                ,null
                ,null
                ,null
                ,null
                ,null
                ,FavoritesColumn._ID + " DESC");
    }

    public long insertProvider(ContentValues values){
        return database.insert(DatabaseConstruct.DB_NAME, null, values);
    }

    public int updateProvider(String id,ContentValues values){
        return database.update(DatabaseConstruct.DB_NAME, values, FavoritesColumn._ID +" = ?",new String[]{id});
    }

    public int deleteProvider(String id){
        return database.delete(DatabaseConstruct.DB_NAME, FavoritesColumn._ID + " = ?", new String[]{id});
    }

}
