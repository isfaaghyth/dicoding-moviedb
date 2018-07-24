package app.isfaaghyth.moviedb.utils;

import app.isfaaghyth.moviedb.BuildConfig;

/**
 * Created by isfaaghyth on 7/25/18.
 * github: @isfaaghyth
 */

public class Consts {

    public static final String POPULAR = "popular"; //default
    public static final String NOW_PLAYING = "now_playing";
    public static final String UPCOMING = "upcoming";

    public static String loadMovieBanner(String fileName) {
        return BuildConfig.IMG_URL + fileName;
    }

}
