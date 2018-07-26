package app.isfaaghyth.moviedb.ui.main.fragment.upcoming;

import app.isfaaghyth.moviedb.base.BaseView;
import app.isfaaghyth.moviedb.data.MovieRepository;

/**
 * Created by isfaaghyth on 7/26/18.
 * github: @isfaaghyth
 */

interface UpcomingView extends BaseView {
    void onSuccess(MovieRepository result);
    void onError(String message);
}
