package app.isfaaghyth.moviedb.ui.searchmovie;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import app.isfaaghyth.moviedb.R;
import app.isfaaghyth.moviedb.adapter.MovieAdapter;
import app.isfaaghyth.moviedb.base.BaseActivity;
import app.isfaaghyth.moviedb.data.Movie;
import app.isfaaghyth.moviedb.data.MovieRepository;
import butterknife.BindView;

/**
 * Created by isfaaghyth on 7/26/18.
 * github: @isfaaghyth
 */

public class SearchActivity extends BaseActivity implements SearchView {

    @BindView(R.id.lst_search) RecyclerView lstSearch;

    private List<Movie> movies = new ArrayList<>();
    private MovieAdapter adapter;

    @Override public int contentView() {
        return R.layout.activity_search;
    }

    @Override public void onCreated() {
        super.showBackBar();

        String searchIntent = getIntent().getStringExtra("keyword");

        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle("\"" + searchIntent + "\"");

        new SearchRequest(this).searchMovie(searchIntent);
        lstSearch.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new MovieAdapter(movies);
        lstSearch.setAdapter(adapter);
    }

    @Override public void onSuccess(MovieRepository result) {
        if (result != null) {
            if (result.getResults().size() > 0) {
                movies.clear();
                movies.addAll(result.getResults());
                adapter.notifyDataSetChanged();
            } else {
                super.onInfo(getString(R.string.movie_not_found));
            }
        }
    }

    @Override public void onError(String message) {
        super.onError(message);
    }

}
