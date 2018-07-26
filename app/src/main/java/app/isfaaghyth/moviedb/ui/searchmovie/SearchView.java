package app.isfaaghyth.moviedb.ui.searchmovie;

import app.isfaaghyth.moviedb.base.BaseView;
import app.isfaaghyth.moviedb.data.MovieRepository;

/**
 * Created by isfaaghyth on 7/26/18.
 * github: @isfaaghyth
 */

interface SearchView extends BaseView {
    void onSuccess(MovieRepository result);
    void onError(String message);
}
