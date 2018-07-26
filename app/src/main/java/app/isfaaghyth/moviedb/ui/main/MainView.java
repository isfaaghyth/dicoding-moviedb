package app.isfaaghyth.moviedb.ui.main;

import android.content.Context;

import app.isfaaghyth.moviedb.base.BaseView;
import app.isfaaghyth.moviedb.data.MovieRepository;

/**
 * Created by isfaaghyth on 7/24/18.
 * github: @isfaaghyth
 */

public interface MainView extends BaseView {
    void onSuccess(MovieRepository result);
    void onError(String message);
}
