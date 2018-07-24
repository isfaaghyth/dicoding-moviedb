package app.isfaaghyth.moviedb.data;

import java.util.List;

/**
 * Created by isfaaghyth on 7/24/18.
 * github: @isfaaghyth
 */

public class MovieRepository {
    private int page;
    private List<Movie> results;

    public int getPage() {
        return page;
    }

    public List<Movie> getResults() {
        return results;
    }
}
