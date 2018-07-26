package app.isfaaghyth.moviedb.ui.detailmovie;

import app.isfaaghyth.moviedb.base.BaseView;
import app.isfaaghyth.moviedb.data.MovieTrailerRepository;

/**
 * Created by isfaaghyth on 7/24/18.
 * github: @isfaaghyth
 */

public interface DetailMovieView extends BaseView {
    void onSuccess(MovieTrailerRepository result);
    void onError(String message);
}
