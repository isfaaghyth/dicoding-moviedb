package app.isfaaghyth.moviedb.data.local;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import app.isfaaghyth.moviedb.data.helper.FavoriteManager;

/**
 * Created by isfaaghyth on 8/8/18.
 * github: @isfaaghyth
 */

public class FavoriteProvider extends ContentProvider {

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    private static final int MOVIE      = 1;
    private static final int MOVIE_ID   = 2;

    private FavoriteManager favoriteManager;

    static {
        sUriMatcher.addURI(DatabaseConstruct.CONTENT_AUTHORITY, DatabaseConstruct.TABLE_FAVORITES, MOVIE);
        sUriMatcher.addURI(DatabaseConstruct.CONTENT_AUTHORITY, DatabaseConstruct.TABLE_FAVORITES + "/#", MOVIE_ID);
    }

    @Override public boolean onCreate() {
        favoriteManager = new FavoriteManager(getContext());
        favoriteManager.open();
        return true;
    }

    @Nullable @Override public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor = (sUriMatcher.match(uri) == MOVIE)
                ? favoriteManager.queryProvider()
                : favoriteManager.queryByIdProvider(uri.getLastPathSegment());
        if (cursor != null) cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable @Override public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable @Override public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        long added = (sUriMatcher.match(uri) == MOVIE)
                ? favoriteManager.insertProvider(values)
                : 0;
        if (added > 0) getContext().getContentResolver().notifyChange(uri, null);
        return Uri.parse(DatabaseConstruct.CONTENT_URI + "/" + added);
    }

    @Override public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int deleted = (sUriMatcher.match(uri) == MOVIE_ID)
                ? favoriteManager.deleteProvider(uri.getLastPathSegment())
                : 0;
        if (deleted > 0) getContext().getContentResolver().notifyChange(uri, null);
        return deleted;
    }

    @Override public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int updated = (sUriMatcher.match(uri) == MOVIE_ID)
                ? favoriteManager.updateProvider(uri.getLastPathSegment(), values)
                : 0;
        if (updated > 0) getContext().getContentResolver().notifyChange(uri, null);
        return updated;
    }
}
