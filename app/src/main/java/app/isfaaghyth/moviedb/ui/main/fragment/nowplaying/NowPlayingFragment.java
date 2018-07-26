package app.isfaaghyth.moviedb.ui.main.fragment.nowplaying;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import app.isfaaghyth.moviedb.R;
import app.isfaaghyth.moviedb.adapter.MovieAdapter;
import app.isfaaghyth.moviedb.base.BaseFragment;
import app.isfaaghyth.moviedb.data.Movie;
import app.isfaaghyth.moviedb.data.MovieRepository;
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

    @Override public void onCreated(View view) {
        final NowPlayingRequest request = new NowPlayingRequest(this);
        request.nowPlaying();

        lstNowPlaying.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapter = new MovieAdapter(movies);
        lstNowPlaying.setAdapter(adapter);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override public void onRefresh() {
                request.nowPlaying();
            }
        });
    }

    @Override public void onSuccess(MovieRepository result) {
        swipeRefresh.setRefreshing(true);
        movies.clear();
        movies.addAll(result.getResults());
        adapter.notifyDataSetChanged();
    }

    @Override public void onError(String message) {
        swipeRefresh.setRefreshing(false);
        super.onError(message);
    }
}
