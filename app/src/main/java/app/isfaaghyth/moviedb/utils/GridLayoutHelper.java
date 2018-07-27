package app.isfaaghyth.moviedb.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by isfaaghyth on 7/27/18.
 * github: @isfaaghyth
 */

public class GridLayoutHelper {

    public static int calc(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        return (int) (dpWidth / 180);
    }

}
