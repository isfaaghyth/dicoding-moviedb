package app.isfaaghyth.moviedb.ui.main.fragment.popular;

import app.isfaaghyth.moviedb.base.BaseView;
import app.isfaaghyth.moviedb.data.model.MovieRepository;

/**
 * Created by isfaaghyth on 7/26/18.
 * github: @isfaaghyth
 */

interface PopularView extends BaseView {
    void onSuccess(MovieRepository result);
    void onError(String message);
}
