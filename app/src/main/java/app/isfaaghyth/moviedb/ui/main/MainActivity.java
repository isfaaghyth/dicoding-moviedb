package app.isfaaghyth.moviedb.ui.main;

import app.isfaaghyth.moviedb.R;
import app.isfaaghyth.moviedb.base.BaseActivity;
import app.isfaaghyth.moviedb.data.MovieRepository;

public class MainActivity extends BaseActivity implements MainView<MovieRepository> {

    private MainRequest request;

    @Override public int contentView() {
        return R.layout.activity_main;
    }

    @Override public void onCreated() {
        request = new MainRequest(this);
    }

    @Override public void onSuccess(MovieRepository result) {

    }

    @Override public void onError(String message) {

    }

}
