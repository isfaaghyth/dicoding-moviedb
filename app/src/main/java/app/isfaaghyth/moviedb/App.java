package app.isfaaghyth.moviedb;

import android.app.Application;

import io.isfaaghyth.rak.Rak;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by isfaaghyth on 7/25/18.
 * github: @isfaaghyth
 */

public class App extends Application {

    @Override public void onCreate() {
        super.onCreate();
        Rak.initialize(this);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/cc-regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
    }

}
