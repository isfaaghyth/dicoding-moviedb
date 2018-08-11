package app.isfaaghyth.favorites;

import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String TABLE_FAVORITES = "favorites";
    private static final String CONTENT_AUTHORITY = "app.isfaaghyth.moviedb";

    public static final Uri CONTENT_URI = new Uri.Builder().scheme("content")
            .authority(CONTENT_AUTHORITY)
            .appendPath(TABLE_FAVORITES)
            .build();

    private FavoritesAdapter adapter;
    private ListView lstFavorites;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lstFavorites = findViewById(R.id.lst_favorites);
        adapter = new FavoritesAdapter(this, null, true);
        lstFavorites.setAdapter(adapter);
        getSupportLoaderManager().initLoader(100, null, this);
    }

    @Override protected void onResume() {
        super.onResume();
        getSupportLoaderManager().restartLoader(100, null, this);
    }

    @NonNull @Override public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        return new CursorLoader(this, CONTENT_URI, null, null, null, null);
    }

    @Override public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }

}
