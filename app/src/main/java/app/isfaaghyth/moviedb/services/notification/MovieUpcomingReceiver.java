package app.isfaaghyth.moviedb.services.notification;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.google.gson.Gson;

import java.util.Calendar;
import java.util.List;

import app.isfaaghyth.moviedb.R;
import app.isfaaghyth.moviedb.data.model.Movie;
import app.isfaaghyth.moviedb.utils.AlarmNotificationUtil;
import app.isfaaghyth.moviedb.utils.AlarmTimeUtils;
import app.isfaaghyth.moviedb.utils.NotificationBuilder;

/**
 * Created by isfaaghyth on 9/12/18.
 * github: @isfaaghyth
 */

public class MovieUpcomingReceiver extends BroadcastReceiver {

    private static int notificationId = 2045;
    private static int UPCOMING_HOUR = 8;

    @Override public void onReceive(Context context, Intent intent) {
        Movie movie = new Gson().fromJson(intent.getStringExtra("movie"), Movie.class);
        NotificationBuilder.sendNotification(
                context, context.getString(R.string.upcoming_notification, movie.getTitle()),
                notificationId, movie);
    }

    public void setUpcomingAlarm(Context context, List<Movie> movies) {
        int notificationDelay = 0;
        Calendar timeAlarm = AlarmTimeUtils.time(UPCOMING_HOUR);
        for (Movie movie: movies) {
            cancelUpcomingAlarm(context);
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(context, MovieUpcomingReceiver.class);
            intent.putExtra("movie", new Gson().toJson(movie));
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmNotificationUtil.set(alarmManager, timeAlarm, notificationDelay, pendingIntent);
            notificationId++;
            notificationDelay += 5000;
        }
    }

    public void cancelUpcomingAlarm(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(getPendingIntent(context));
    }

    private static PendingIntent getPendingIntent(Context context) {
        Intent intent = new Intent(context, MovieUpcomingReceiver.class);
        return PendingIntent.getBroadcast(context, notificationId, intent, PendingIntent.FLAG_CANCEL_CURRENT);
    }

}
