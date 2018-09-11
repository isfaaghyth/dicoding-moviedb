package app.isfaaghyth.moviedb.utils;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

import app.isfaaghyth.moviedb.BuildConfig;
import app.isfaaghyth.moviedb.R;
import app.isfaaghyth.moviedb.data.model.Movie;
import app.isfaaghyth.moviedb.ui.detailmovie.DetailMovieActivity;
import app.isfaaghyth.moviedb.ui.main.MainActivity;

/**
 * Created by isfaaghyth on 9/12/18.
 * github: @isfaaghyth
 */

public class NotificationBuilder {

    public static void sendNotification(Context context, String description, int id) {
        Intent intent = new Intent(context, MainActivity.class);
        notification(context, intent, description, id);
    }

    public static void sendNotification(Context context, String description, int id, Movie movie) {
        Intent intent = new Intent(context, DetailMovieActivity.class);
        intent.putExtra("movie", movie);
        notification(context, intent, description, id);
    }

    private static void notification(Context context, Intent intent, String description, int id) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(
                Context.NOTIFICATION_SERVICE);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, id, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Uri uriTone = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_fav)
                .setContentTitle(context.getString(R.string.app_name))
                .setContentText(description)
                .setContentIntent(pendingIntent)
                .setColor(ContextCompat.getColor(context, android.R.color.transparent))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setSound(uriTone);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(BuildConfig.NOTIFICATION_ID,
                    "NOTIFY_NAME", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.YELLOW);

            builder.setChannelId(BuildConfig.NOTIFICATION_ID);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        notificationManager.notify(id, builder.build());
    }

}
