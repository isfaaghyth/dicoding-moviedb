package app.isfaaghyth.moviedb.services.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

import app.isfaaghyth.moviedb.ui.widget.MovieRemoteViewsFactory;

/**
 * Created by isfaaghyth on 9/12/18.
 * github: @isfaaghyth
 */

public class MovieWidgetService extends RemoteViewsService {

    @Override public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new MovieRemoteViewsFactory(this);
    }

}
