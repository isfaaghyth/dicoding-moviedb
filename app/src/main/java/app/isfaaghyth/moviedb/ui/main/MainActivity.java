package app.isfaaghyth.moviedb.ui.main;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import app.isfaaghyth.moviedb.R;
import app.isfaaghyth.moviedb.base.BaseActivity;
import app.isfaaghyth.moviedb.data.Movie;
import app.isfaaghyth.moviedb.data.MovieRepository;
import app.isfaaghyth.moviedb.utils.Consts;
import butterknife.BindView;
import de.mateware.snacky.Snacky;
import io.isfaaghyth.rak.Rak;

public class MainActivity extends BaseActivity implements MainView, SwipeRefreshLayout.OnRefreshListener {

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
        request.popularMovies(Consts.POPULAR); //default

        swipeRefresh.setOnRefreshListener(this);

        lstMovies.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new MainAdapter(movies);
        lstMovies.setAdapter(adapter);
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_movie_filter, menu);

        SearchView searchMovieView = (SearchView) menu.findItem(R.id.mn_search).getActionView();
        searchMovieView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override public boolean onQueryTextSubmit(String query) {
                request.searchMovieByKeyword(query);
                return false;
            }

            @Override public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mn_popular:
                request.popularMovies(Consts.POPULAR);
                break;
            case R.id.mn_upcoming:
                request.popularMovies(Consts.UPCOMING);
                break;
            case R.id.mn_now_showing:
                request.popularMovies(Consts.NOW_PLAYING);
                break;
            default:
                request.popularMovies(Consts.POPULAR);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override public void onSuccess(MovieRepository result) {
        if (result.getResults().size() > 0) {
            if (result.getPage() == 1) movies.clear(); //selain page 1 di append
            swipeRefresh.setRefreshing(false);
            movies.addAll(result.getResults());
            adapter.notifyDataSetChanged();
        } else {
            onInfo("Tidak ada movie.");
        }
    }

    @Override public void onError(String message) {
        swipeRefresh.setRefreshing(false);
        super.onError(message);
    }

    @Override public void onRefresh() {
        request.popularMovies(Consts.POPULAR);
        swipeRefresh.setRefreshing(true);
    }
}
