package app.isfaaghyth.moviedb.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by isfaaghyth on 7/24/18.
 * github: @isfaaghyth
 */

public abstract class BaseActivity extends AppCompatActivity {

    public abstract int contentView();
    public abstract void onCreated();

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setContentView(contentView());
        onCreated();
    }

}
