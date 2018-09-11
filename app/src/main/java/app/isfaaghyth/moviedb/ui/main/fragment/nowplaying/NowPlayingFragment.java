package app.isfaaghyth.moviedb.ui.main.fragment.nowplaying;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;

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

public class NowPlayingFragment extends BaseFragment implements NowPlayingView {

    @BindView(R.id.swipe_refresh) SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.lst_now_playing) RecyclerView lstNowPlaying;

    private List<Movie> movies = new ArrayList<>();
    private MovieAdapter adapter;

    @Override public int contentView() {
        return R.layout.fragment_now_playing;
    }

    @Override public void onCreated(View view, Bundle savedInstanceState) {
        final NowPlayingRequest request = new NowPlayingRequest(this);
        if (savedInstanceState != null) {
            movies = savedInstanceState.getParcelableArrayList("movies");
        } else {
            request.nowPlaying();
        }

        nowPlayingListPrepared();
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override public void onRefresh() {
                request.nowPlaying();
            }
        });
    }

    private void nowPlayingListPrepared() {
        lstNowPlaying.setLayoutManager(new GridLayoutManager(
                getContext(), GridLayoutHelper.calc(getContext())));
        adapter = new MovieAdapter(false, movies);
        lstNowPlaying.setAdapter(adapter);
    }

    @Override public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("movies", new ArrayList<>(adapter.getMovies()));
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
