package app.isfaaghyth.moviedb.ui.main;

import android.util.Log;

import app.isfaaghyth.moviedb.R;
import app.isfaaghyth.moviedb.base.BaseActivity;
import app.isfaaghyth.moviedb.data.Movie;
import app.isfaaghyth.moviedb.data.MovieRepository;
import de.mateware.snacky.Snacky;

public class MainActivity extends BaseActivity implements MainView<MovieRepository> {

    @Override public int contentView() {
        return R.layout.activity_main;
    }

    @Override public void onCreated() {
        MainRequest request = new MainRequest(this);
        request.popularMovies();
    }

    @Override public void onSuccess(MovieRepository result) {
        for (Movie m: result.getResults()) {
            Log.d("TAG", m.getTitle());
        }
    }

    @Override public void onError(String message) {
        super.onError(message);
    }

}
