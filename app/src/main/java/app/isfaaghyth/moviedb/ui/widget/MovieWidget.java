package app.isfaaghyth.moviedb.ui.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;
import android.widget.Toast;

import app.isfaaghyth.moviedb.R;
import app.isfaaghyth.moviedb.services.widget.MovieWidgetService;

/**
 * Implementation of App Widget functionality.
 */
public class MovieWidget extends AppWidgetProvider {

    public static final String INTENT_ITEM = "app.isfaaghyth.moviedb.INTENT_ITEM";
    public static final String ACTION_ITEM = "app.isfaaghyth.moviedb.ACTION_ITEM";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        Intent intent = new Intent(context, MovieWidgetService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_movie);
        views.setRemoteAdapter(R.id.stack_movie, intent);
        views.setEmptyView(R.id.stack_movie, R.id.txt_not_found);

        Intent toastIntent = new Intent(context, MovieWidget.class);
        toastIntent.setAction(MovieWidget.ACTION_ITEM);
        toastIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));

        PendingIntent toastPendingIntent = PendingIntent.getBroadcast(
                context, 0, toastIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        views.setPendingIntentTemplate(R.id.stack_movie, toastPendingIntent);

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ACTION_ITEM)) {
            int viewIndex = intent.getIntExtra(INTENT_ITEM, 0);
            //TODO("implementasi deeplink untuk ke detailMovie")
            Toast.makeText(context, viewIndex, Toast.LENGTH_SHORT).show();
        }

        super.onReceive(context, intent);
    }
}

