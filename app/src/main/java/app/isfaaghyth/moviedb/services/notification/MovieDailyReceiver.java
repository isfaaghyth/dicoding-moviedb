package app.isfaaghyth.moviedb.services.notification;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import java.util.Calendar;

import app.isfaaghyth.moviedb.BuildConfig;
import app.isfaaghyth.moviedb.R;
import app.isfaaghyth.moviedb.utils.AlarmNotificationUtil;
import app.isfaaghyth.moviedb.utils.AlarmTimeUtils;
import app.isfaaghyth.moviedb.utils.NotificationBuilder;

/**
 * Created by isfaaghyth on 9/12/18.
 * github: @isfaaghyth
 */

public class MovieDailyReceiver extends BroadcastReceiver {

    private static int notificationId = Integer.valueOf(BuildConfig.NOTIFICATION_ID);
    private static int DAILY_HOUR = 7;

    @Override public void onReceive(Context context, Intent intent) {
        NotificationBuilder.sendNotification(context, context.getString(R.string.daily_notification), notificationId);
    }

    public void setDailyAlarm(Context context) {
        cancelDailyAlarm(context);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Calendar timeAlarm = AlarmTimeUtils.time(DAILY_HOUR);
        AlarmNotificationUtil.set(alarmManager, timeAlarm, 1, getPendingIntent(context));
    }

    public void cancelDailyAlarm(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(getPendingIntent(context));
    }

    private static PendingIntent getPendingIntent(Context context) {
        Intent intent = new Intent(context, MovieDailyReceiver.class);
        return PendingIntent.getBroadcast(context, notificationId, intent, PendingIntent.FLAG_CANCEL_CURRENT);
    }

}
