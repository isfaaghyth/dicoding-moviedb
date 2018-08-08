package app.isfaaghyth.moviedb.data.local;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by isfaaghyth on 8/8/18.
 * github: @isfaaghyth
 */

public class FavoriteProvider extends ContentProvider {

    private static final int MOVIE = 1;
    private static final int MOVIE_ID = 2;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    private FavoriteManager favoriteManager;

    static {
        sUriMatcher.addURI(DatabaseConstruct.CONTENT_AUTHORITY, DatabaseConstruct.TABLE_FAVORITES, MOVIE);
        sUriMatcher.addURI(DatabaseConstruct.CONTENT_AUTHORITY,
                DatabaseConstruct.TABLE_FAVORITES + "/#",
                MOVIE_ID);
    }

    @Override public boolean onCreate() {
        favoriteManager = new FavoriteManager(getContext());
        favoriteManager.open();
        return true;
    }

    @Nullable @Override public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor;
        switch(sUriMatcher.match(uri)){
            case MOVIE:
                cursor = favoriteManager.queryProvider();
                break;
            case MOVIE_ID:
                cursor = favoriteManager.queryByIdProvider(uri.getLastPathSegment());
                break;
            default:
                cursor = null;
                break;
        }

        if (cursor != null){
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
        }

        return cursor;
    }

    @Nullable @Override public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable @Override public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        long added;

        switch (sUriMatcher.match(uri)){
            case MOVIE:
                added = favoriteManager.insertProvider(values);
                break;
            default:
                added = 0;
                break;
        }

        if (added > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return Uri.parse(DatabaseConstruct.CONTENT_URI + "/" + added);
    }

    @Override public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int deleted;
        switch (sUriMatcher.match(uri)) {
            case MOVIE_ID:
                deleted =  favoriteManager.deleteProvider(uri.getLastPathSegment());
                break;
            default:
                deleted = 0;
                break;
        }

        if (deleted > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return deleted;
    }

    @Override public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int updated;
        switch (sUriMatcher.match(uri)) {
            case MOVIE_ID:
                updated =  favoriteManager.updateProvider(uri.getLastPathSegment(), values);
                break;
            default:
                updated = 0;
                break;
        }

        if (updated > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return updated;
    }
}
