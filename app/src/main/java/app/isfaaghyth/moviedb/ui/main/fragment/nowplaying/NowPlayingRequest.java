package app.isfaaghyth.moviedb.ui.main.fragment.nowplaying;

import android.support.annotation.NonNull;

import app.isfaaghyth.moviedb.BuildConfig;
import app.isfaaghyth.moviedb.base.BaseRequest;
import app.isfaaghyth.moviedb.data.MovieRepository;
import app.isfaaghyth.moviedb.utils.Consts;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by isfaaghyth on 7/26/18.
 * github: @isfaaghyth
 */

class NowPlayingRequest extends BaseRequest {

    private NowPlayingView view;

    NowPlayingRequest(NowPlayingView view) {
        this.view = view;
        initialize();
    }

    void nowPlaying() {
        view.showLoader();
        getRequest().getMovies(Consts.NOW_PLAYING, BuildConfig.API_KEY).enqueue(new Callback<MovieRepository>() {
            @Override public void onResponse(@NonNull Call<MovieRepository> call, @NonNull Response<MovieRepository> response) {
                view.hideLoader();
                if (response.isSuccessful()) {
                    view.onSuccess(response.body());
                }
            }

            @Override public void onFailure(@NonNull Call<MovieRepository> call, @NonNull Throwable t) {
                view.hideLoader();
                view.onError(t.getMessage());
            }
        });
    }

}
