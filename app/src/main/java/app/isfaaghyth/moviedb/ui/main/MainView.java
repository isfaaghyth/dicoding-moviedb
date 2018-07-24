package app.isfaaghyth.moviedb.ui.main;

import android.content.Context;

import app.isfaaghyth.moviedb.base.RequestListener;

/**
 * Created by isfaaghyth on 7/24/18.
 * github: @isfaaghyth
 */

public interface MainView<M> extends RequestListener<M> {
    Context context();
}
