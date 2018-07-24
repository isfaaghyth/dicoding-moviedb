package app.isfaaghyth.moviedb.data;

import java.util.List;

/**
 * Created by isfaaghyth on 7/24/18.
 * github: @isfaaghyth
 */

public class Movie {
    private int id;
    private float vote_average;
    private String title;
    private String poster_path;
    private List<Integer> genre_ids;
    private String backdrop_path;
    private String overview;
    private String release_date;

    public int getId() {
        return id;
    }

    public float getVote_average() {
        return vote_average;
    }

    public String getTitle() {
        return title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getOverview() {
        return overview;
    }

    public String getRelease_date() {
        return release_date;
    }
}
