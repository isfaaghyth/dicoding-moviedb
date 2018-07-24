package app.isfaaghyth.moviedb.utils;

import android.content.Context;
import android.content.Intent;

/**
 * Created by isfaaghyth on 7/25/18.
 * github: @isfaaghyth
 */

public class ShareIntentUtil {

    public static void shareMovie(Context context, String... data) {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");

        String movieName = data[0];
        String rating = "\u2605" + data[1];
        String review = data[2];

        String content = movieName + " " + rating + "\n" +
                review + " - from @themoviedb.org";

        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Daeng Movies");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, content);
        context.startActivity(Intent.createChooser(sharingIntent, "Share in your friends"));
    }

}
