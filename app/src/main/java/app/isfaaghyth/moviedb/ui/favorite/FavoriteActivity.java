package app.isfaaghyth.moviedb.ui.favorite;

import android.support.v4.app.LoaderManager;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import app.isfaaghyth.moviedb.R;
import app.isfaaghyth.moviedb.adapter.FavoritesAdapter;
import app.isfaaghyth.moviedb.base.BaseActivity;
import app.isfaaghyth.moviedb.base.BaseView;
import app.isfaaghyth.moviedb.data.local.DatabaseConstruct;
import app.isfaaghyth.moviedb.utils.GridLayoutHelper;
import butterknife.BindView;

/**
 * Created by isfaaghyth on 8/9/18.
 * github: @isfaaghyth
 */

public class FavoriteActivity extends BaseActivity implements BaseView, LoaderManager.LoaderCallbacks<Cursor> {

    private static final int LOADER_ID = 321;
    private FavoritesAdapter adapter;

    @BindView(R.id.lst_favorites) RecyclerView lstFavorites;

    @Override public int contentView() {
        return R.layout.activity_favorites;
    }

    @Override public void onCreated() {
        super.showBackBar();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.favorites));
        }

        lstFavorites.setLayoutManager(new GridLayoutManager(this, GridLayoutHelper.calc(this)));
        adapter = new FavoritesAdapter(this, null);
        lstFavorites.setAdapter(adapter);
        getSupportLoaderManager().initLoader(LOADER_ID, null, this);
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        getSupportLoaderManager().destroyLoader(LOADER_ID);
    }

    @Override public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, DatabaseConstruct.CONTENT_URI, null, null, null, null);
    }

    @Override public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
        adapter.notifyDataSetChanged();
    }

    @Override public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }

}
