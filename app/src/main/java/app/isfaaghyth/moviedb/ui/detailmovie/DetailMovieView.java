package app.isfaaghyth.moviedb.ui.detailmovie;

import app.isfaaghyth.moviedb.base.BaseView;
import app.isfaaghyth.moviedb.data.model.MovieTrailerRepository;

/**
 * Created by isfaaghyth on 7/24/18.
 * github: @isfaaghyth
 */

interface DetailMovieView extends BaseView {
    void onSuccessTrailer(MovieTrailerRepository result);
    void onError(String message);
}
