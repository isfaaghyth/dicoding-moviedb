package app.isfaaghyth.moviedb.ui.main.fragment.popular;

import android.content.res.Configuration;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import app.isfaaghyth.moviedb.R;
import app.isfaaghyth.moviedb.adapter.HighLightAdapter;
import app.isfaaghyth.moviedb.adapter.MovieAdapter;
import app.isfaaghyth.moviedb.base.BaseFragment;
import app.isfaaghyth.moviedb.data.Movie;
import app.isfaaghyth.moviedb.data.MovieRepository;
import app.isfaaghyth.moviedb.utils.GridLayoutHelper;
import app.isfaaghyth.moviedb.utils.StartSnapHelper;
import butterknife.BindView;
import io.isfaaghyth.rak.Rak;

/**
 * Created by isfaaghyth on 7/26/18.
 * github: @isfaaghyth
 */

public class PopularFragment extends BaseFragment implements PopularView {

    @BindView(R.id.lst_popular) RecyclerView lstPopular;
    @BindView(R.id.lst_highlight) RecyclerView lstHighlight;

    private List<Movie> movies = new ArrayList<>();
    private MovieAdapter popularAdapter;
    private HighLightAdapter highlightAdapter;

    @Override public int contentView() {
        return R.layout.fragment_popular;
    }

    @Override public void onCreated(View view) {
        PopularRequest request = new PopularRequest(this);
        request.popular();
        popularListPrepared();
    }

    private void popularListPrepared() {
        lstPopular.setLayoutManager(new GridLayoutManager(
                getContext(), GridLayoutHelper.calc(getContext())));
        lstPopular.setNestedScrollingEnabled(false);
        popularAdapter = new MovieAdapter(movies);
        lstPopular.setAdapter(popularAdapter);
    }

    private void highlightListPrepared(List<Movie> movies) {
        List<Movie> highLightMovies = new ArrayList<>();
        highLightMovies.add(movies.get(0));
        highLightMovies.add(movies.get(1));
        highLightMovies.add(movies.get(2));
        lstHighlight.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));
        SnapHelper snapHelper = new StartSnapHelper();
        snapHelper.attachToRecyclerView(lstHighlight);
        highlightAdapter = new HighLightAdapter(highLightMovies);
        lstHighlight.setAdapter(highlightAdapter);
    }

    @Override public void onSuccess(MovieRepository result) {
        movies.clear();
        for (int i = 0; i < result.getResults().size(); i++) {
            if (i < 3) continue; //skip yang udah ada di highlight
            Movie movie = result.getResults().get(i);
            movies.add(movie);
        }
        popularAdapter.notifyDataSetChanged();
        highlightListPrepared(result.getResults());
    }

    @Override public void onError(String message) {
        super.onError(message);
    }

}
