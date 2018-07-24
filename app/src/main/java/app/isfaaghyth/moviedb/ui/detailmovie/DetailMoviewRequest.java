package app.isfaaghyth.moviedb.ui.detailmovie;

import app.isfaaghyth.moviedb.base.BaseRequest;

/**
 * Created by isfaaghyth on 7/24/18.
 * github: @isfaaghyth
 */

public class DetailMoviewRequest extends BaseRequest {

    private DetailMovieView view;

    public DetailMoviewRequest(DetailMovieView view) {
        this.view = view;
        initialize();
    }

}
