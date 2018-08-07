package app.isfaaghyth.moviedb.data.local;

import android.provider.BaseColumns;

/**
 * Created by isfaaghyth on 8/7/18.
 * github: @isfaaghyth
 */

public class FavoritesColumn implements BaseColumns {

    private static final String id = "id";
    private static final String vote_average = "vote_average";
    private static final String vote_count = "vote_count";
    private static final String title = "title";
    private static final String poster_path = "poster_path";
    private static final String backdrop_path = "backdrop_path";
    private static final String overview = "overview";
    private static final String release_date = "release_date";

    public static String getId() {
        return id;
    }

    public static String getVote_average() {
        return vote_average;
    }

    public static String getVote_count() {
        return vote_count;
    }

    public static String getTitle() {
        return title;
    }

    public static String getPoster_path() {
        return poster_path;
    }

    public static String getBackdrop_path() {
        return backdrop_path;
    }

    public static String getOverview() {
        return overview;
    }

    public static String getRelease_date() {
        return release_date;
    }
}
