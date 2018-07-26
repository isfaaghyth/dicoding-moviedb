package app.isfaaghyth.moviedb.ui.main;

import android.support.annotation.NonNull;

import app.isfaaghyth.moviedb.BuildConfig;
import app.isfaaghyth.moviedb.base.BaseRequest;
import app.isfaaghyth.moviedb.data.MovieRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by isfaaghyth on 7/24/18.
 * github: @isfaaghyth
 */

class MainRequest extends BaseRequest {

    private MainView view;

    MainRequest(MainView view) {
        this.view = view;
        initialize();
    }

//    void searchMovieByKeyword(String keyword) {
//        view.showLoader();
//        getRequest().getMovieByQuery(keyword, BuildConfig.API_KEY)
//                .enqueue(new Callback<MovieRepository>() {
//            @Override public void onResponse(@NonNull Call<MovieRepository> call,
//                                             @NonNull Response<MovieRepository> response) {
//                if (response.isSuccessful()) {
//                    view.hideLoader();
//                    view.onSuccess(response.body());
//                }
//            }
//
//            @Override public void onFailure(@NonNull Call<MovieRepository> call, @NonNull Throwable t) {
//                view.hideLoader();
//                view.onError(t.getMessage());
//            }
//        });
//    }

}
