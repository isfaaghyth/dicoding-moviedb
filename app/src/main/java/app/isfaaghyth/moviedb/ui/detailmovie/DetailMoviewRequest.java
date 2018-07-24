package app.isfaaghyth.moviedb.ui.detailmovie;

import android.support.annotation.NonNull;

import app.isfaaghyth.moviedb.BuildConfig;
import app.isfaaghyth.moviedb.base.BaseRequest;
import app.isfaaghyth.moviedb.data.MovieTrailerRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by isfaaghyth on 7/24/18.
 * github: @isfaaghyth
 */

public class DetailMoviewRequest extends BaseRequest {

    private DetailMovieView view;

    public DetailMoviewRequest(DetailMovieView view) {
        this.view = view;
        initialize();
        initProgress(view.context());
    }

    void movieTrailer(int movieId) {
        getLoader().show();
        getRequest().getTrailerMovie(String.valueOf(movieId), BuildConfig.API_KEY).enqueue(new Callback<MovieTrailerRepository>() {
            @Override public void onResponse(@NonNull Call<MovieTrailerRepository> call, @NonNull Response<MovieTrailerRepository> response) {
                if (response.isSuccessful()) {
                    getLoader().hide();
                    view.onSuccess(response.body());
                }
            }

            @Override public void onFailure(@NonNull Call<MovieTrailerRepository> call, @NonNull Throwable t) {
                getLoader().hide();
                view.onError(t.getMessage());
            }
        });
    }

}
