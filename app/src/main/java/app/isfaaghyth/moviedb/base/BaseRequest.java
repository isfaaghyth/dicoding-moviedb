package app.isfaaghyth.moviedb.base;

import app.isfaaghyth.moviedb.network.Network;
import app.isfaaghyth.moviedb.network.Routes;

/**
 * Created by isfaaghyth on 7/24/18.
 * github: @isfaaghyth
 */

public class BaseRequest {

    protected Routes network;

    protected void initialize() {
        network = Network.builder().create(Routes.class);
    }

}
