package app.isfaaghyth.moviedb.ui.main;

import app.isfaaghyth.moviedb.base.BaseRequest;

/**
 * Created by isfaaghyth on 7/24/18.
 * github: @isfaaghyth
 */

public class MainRequest extends BaseRequest {

    private MainView view;

    MainRequest(MainView view) {
        this.view = view;
        initialize();
    }

}
