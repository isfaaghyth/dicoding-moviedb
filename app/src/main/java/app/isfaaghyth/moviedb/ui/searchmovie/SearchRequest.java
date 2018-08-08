package app.isfaaghyth.moviedb.ui.searchmovie;

import android.support.annotation.NonNull;

import java.util.Locale;

import app.isfaaghyth.moviedb.BuildConfig;
import app.isfaaghyth.moviedb.base.BaseRequest;
import app.isfaaghyth.moviedb.data.model.MovieRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by isfaaghyth on 7/26/18.
 * github: @isfaaghyth
 */

class SearchRequest extends BaseRequest {

    private SearchView view;

    public SearchRequest(SearchView view) {
        this.view = view;
        initialize();
    }

    void searchMovie(String keyword) {
        view.showLoader();
        getRequest().getMovieByQuery(keyword,
                Locale.getDefault().toString(), BuildConfig.API_KEY).enqueue(new Callback<MovieRepository>() {
            @Override public void onResponse(@NonNull Call<MovieRepository> call,
                                             @NonNull Response<MovieRepository> response) {
                view.hideLoader();
                view.onSuccess(response.isSuccessful() ? response.body() : null);
            }

            @Override public void onFailure(@NonNull Call<MovieRepository> call,
                                            @NonNull Throwable t) {
                view.hideLoader();
                view.onError(t.getMessage());
            }
        });
    }

}
