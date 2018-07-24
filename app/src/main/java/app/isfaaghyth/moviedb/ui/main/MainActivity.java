package app.isfaaghyth.moviedb.ui.main;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;

import java.util.ArrayList;
import java.util.List;

import app.isfaaghyth.moviedb.R;
import app.isfaaghyth.moviedb.base.BaseActivity;
import app.isfaaghyth.moviedb.data.Movie;
import app.isfaaghyth.moviedb.data.MovieRepository;
import butterknife.BindView;
import de.mateware.snacky.Snacky;

public class MainActivity extends BaseActivity implements MainView<MovieRepository>, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.lst_movies) RecyclerView lstMovies;
    @BindView(R.id.swipe_refresh_main) SwipeRefreshLayout swipeRefresh;

    private List<Movie> movies = new ArrayList<>();
    private MainRequest request;
    private MainAdapter adapter;

    @Override public int contentView() {
        return R.layout.activity_main;
    }

    @Override public void onCreated() {
        request = new MainRequest(this);
        request.popularMovies();

        swipeRefresh.setOnRefreshListener(this);

        lstMovies.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new MainAdapter(movies);
        lstMovies.setAdapter(adapter);
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_movie_filter, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override public void onSuccess(MovieRepository result) {
        swipeRefresh.setRefreshing(false);
        movies.clear();
        movies.addAll(result.getResults());
        adapter.notifyDataSetChanged();
    }

    @Override public void onError(String message) {
        swipeRefresh.setRefreshing(false);
        super.onError(message);
    }

    @Override public Context context() {
        return MainActivity.this;
    }

    @Override public void onRefresh() {
        request.popularMovies();
        swipeRefresh.setRefreshing(true);
    }
}
