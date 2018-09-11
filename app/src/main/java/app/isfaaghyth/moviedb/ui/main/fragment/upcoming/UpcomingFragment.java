package app.isfaaghyth.moviedb.ui.main.fragment.upcoming;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import app.isfaaghyth.moviedb.R;
import app.isfaaghyth.moviedb.adapter.MovieAdapter;
import app.isfaaghyth.moviedb.base.BaseFragment;
import app.isfaaghyth.moviedb.data.model.Movie;
import app.isfaaghyth.moviedb.data.model.MovieRepository;
import app.isfaaghyth.moviedb.utils.GridLayoutHelper;
import butterknife.BindView;

/**
 * Created by isfaaghyth on 7/26/18.
 * github: @isfaaghyth
 */

public class UpcomingFragment extends BaseFragment implements UpcomingView {

    @BindView(R.id.swipe_refresh) SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.lst_upcoming) RecyclerView lstUpcoming;

    private List<Movie> movies = new ArrayList<>();
    private MovieAdapter adapter;

    @Override public int contentView() {
        return R.layout.fragment_upcoming;
    }

    @Override public void onCreated(View view, Bundle savedInstanceState) {
        final UpcomingRequest request = new UpcomingRequest(this);
        if (savedInstanceState != null) {
            movies = savedInstanceState.getParcelableArrayList("movies");
        } else {
            request.upcoming();
        }

        upcomingListPrepared();
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override public void onRefresh() {
                request.upcoming();
            }
        });
    }

    @Override public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("movies", new ArrayList<>(adapter.getMovies()));
    }

    private void upcomingListPrepared() {
        lstUpcoming.setLayoutManager(new GridLayoutManager(
                getContext(), GridLayoutHelper.calc(getContext())));
        adapter = new MovieAdapter(false, movies);
        lstUpcoming.setAdapter(adapter);
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

}
