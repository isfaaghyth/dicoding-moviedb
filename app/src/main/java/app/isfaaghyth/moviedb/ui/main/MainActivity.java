package app.isfaaghyth.moviedb.ui.main;

import app.isfaaghyth.moviedb.R;
import app.isfaaghyth.moviedb.base.BaseActivity;
import app.isfaaghyth.moviedb.base.RequestListener;
import app.isfaaghyth.moviedb.data.Data;

public class MainActivity extends BaseActivity implements MainView<Data> {

    private MainRequest request;

    @Override public int contentView() {
        return R.layout.activity_main;
    }

    @Override public void onCreated() {
        request = new MainRequest(this);
    }

    @Override public void onSuccess(Data result) {

    }

    @Override public void onError(String message) {

    }

}
