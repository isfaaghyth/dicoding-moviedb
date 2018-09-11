package app.isfaaghyth.moviedb.ui.widget;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Binder;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.bumptech.glide.Glide;

import java.util.concurrent.ExecutionException;

import app.isfaaghyth.moviedb.BuildConfig;
import app.isfaaghyth.moviedb.R;
import app.isfaaghyth.moviedb.data.local.DatabaseConstruct;
import app.isfaaghyth.moviedb.data.local.FavoritesColumn;
import app.isfaaghyth.moviedb.data.model.Movie;
import app.isfaaghyth.moviedb.utils.Consts;

/**
 * Created by isfaaghyth on 9/12/18.
 * github: @isfaaghyth
 */

public class MovieRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private Context context;
    private Cursor cursor;

    public MovieRemoteViewsFactory(Context context) {
        this.context = context;
    }

    @Override public void onCreate() {
        initDatabase();
    }

    @Override public void onDataSetChanged() {
        if (cursor != null) cursor.close();
        long token = Binder.clearCallingIdentity();
        initDatabase();
        Binder.restoreCallingIdentity(token);
    }

    private void initDatabase() {
        cursor = context.getContentResolver().query(
                DatabaseConstruct.CONTENT_URI,
                null, null, null, null);
    }

    @Override public void onDestroy() {
        if (cursor == null) return;
        cursor.close();
    }

    @Override public int getCount() {
        return cursor.getCount();
    }

    @Override public RemoteViews getViewAt(int position) {
        Movie favoriteMovie = getMovie(position);
        RemoteViews widgetView = new RemoteViews(context.getPackageName(), R.layout.widget_movie_item);

        try {
            Bitmap bitmapMovieBackdrop = Glide.with(context)
                    .asBitmap()
                    .load(Consts.loadMovieBanner(favoriteMovie.getBackdrop_path()))
                    .submit()
                    .get();

            widgetView.setImageViewBitmap(R.id.img_poster, bitmapMovieBackdrop);
            widgetView.setTextViewText(R.id.txt_movie_name, favoriteMovie.getTitle());
        } catch (InterruptedException | ExecutionException e){
            Log.d(BuildConfig.APPLICATION_ID, "oops!");
        }

        Bundle extras = new Bundle();
        extras.putInt(MovieWidget.INTENT_ITEM, position);

        Intent fillInIntent = new Intent();
        fillInIntent.putExtras(extras);

        widgetView.setOnClickFillInIntent(R.id.img_poster, fillInIntent);

        return widgetView;
    }

    @Override public RemoteViews getLoadingView() {
        return null;
    }

    @Override public int getViewTypeCount() {
        return 1;
    }

    @Override public long getItemId(int position) {
        return cursor.moveToPosition(position) ? cursor.getLong(0) : position;
    }

    @Override public boolean hasStableIds() {
        return false;
    }

    private Movie getMovie(int position) {
        if (!cursor.moveToPosition(position)) throw new IllegalStateException("oops!");
        return new Movie(cursor.getInt(cursor.getColumnIndexOrThrow(
                FavoritesColumn._ID)),
                cursor.getString(cursor.getColumnIndexOrThrow(FavoritesColumn.getTitle())),
                cursor.getString(cursor.getColumnIndexOrThrow(FavoritesColumn.getBackdrop_path())));
    }
}
