package app.isfaaghyth.moviedb.base;

import android.content.Context;

import app.isfaaghyth.moviedb.network.Network;
import app.isfaaghyth.moviedb.network.Routes;
import app.isfaaghyth.moviedb.utils.ProgressLoader;

/**
 * Created by isfaaghyth on 7/24/18.
 * github: @isfaaghyth
 */

public class BaseRequest {

    private Routes request;
    private ProgressLoader loader;

    protected void initialize() {
        request = Network.builder().create(Routes.class);
    }

    protected void initProgress(Context context) {
        loader = new ProgressLoader(context);
    }

    public Routes getRequest() {
        return request;
    }

    public ProgressLoader getLoader() {
        return loader;
    }
}
