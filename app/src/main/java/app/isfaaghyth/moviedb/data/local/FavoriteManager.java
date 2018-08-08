package app.isfaaghyth.moviedb.data.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import app.isfaaghyth.moviedb.data.Movie;

import static android.provider.BaseColumns._ID;

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

    public long insert(Movie movie) {
        Log.d("TAG", "masuk");
        return database.insert(DatabaseConstruct.TABLE_FAVORITES, null, movieContentValues(movie));
    }

    private ContentValues movieContentValues(Movie movie) {
        ContentValues values = new ContentValues();
        values.put(FavoritesColumn._ID, String.valueOf(movie.getId()));
        values.put(FavoritesColumn.getVote_average(), movie.getVote_average());
        values.put(FavoritesColumn.getVote_count(), movie.getVote_count());
        values.put(FavoritesColumn.getTitle(), movie.getTitle());
        values.put(FavoritesColumn.getPoster_path(), movie.getPoster_path());
        values.put(FavoritesColumn.getBackdrop_path(), movie.getBackdrop_path());
        values.put(FavoritesColumn.getOverview(), movie.getOverview());
        values.put(FavoritesColumn.getRelease_date(), movie.getRelease_date());
        return values;
    }

    public int delete(int id) {
        Log.d("TAG", "kehapus");
        return database.delete(DatabaseConstruct.TABLE_FAVORITES, _ID + "='"+id+"'", null);
    }

    public Cursor queryByIdProvider(String id){
        return database.query(DatabaseConstruct.TABLE_FAVORITES, null
                , _ID + " = ?"
                ,new String[]{id}
                ,null
                ,null
                ,null
                ,null);
    }

    public Cursor queryProvider(){
        return database.query(DatabaseConstruct.TABLE_FAVORITES
                ,null
                ,null
                ,null
                ,null
                ,null
                , _ID + " DESC");
    }

    public long insertProvider(ContentValues values){
        return database.insert(DatabaseConstruct.TABLE_FAVORITES, null, values);
    }

    public int updateProvider(String id, Movie movie){
        return database.update(DatabaseConstruct.TABLE_FAVORITES, movieContentValues(movie), _ID +" = ?",new String[]{id});
    }

    public int updateProvider(String id, ContentValues values){
        return database.update(DatabaseConstruct.TABLE_FAVORITES, values, _ID +" = ?",new String[]{id});
    }

    public int deleteProvider(String id){
        return database.delete(DatabaseConstruct.TABLE_FAVORITES, _ID + " = ?", new String[]{id});
    }

}
