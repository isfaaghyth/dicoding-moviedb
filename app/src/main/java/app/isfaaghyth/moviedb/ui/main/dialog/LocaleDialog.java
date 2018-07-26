package app.isfaaghyth.moviedb.ui.main.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.text.Html;

import app.isfaaghyth.moviedb.R;

/**
 * Created by isfaaghyth on 7/26/18.
 * github: @isfaaghyth
 */

public class LocaleDialog {

    public static void show(Context context, final AlertListener listener) {
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle(context.getString(R.string.information));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            alert.setMessage(Html.fromHtml(
                    context.getString(R.string.locale_message),
                    Html.FROM_HTML_MODE_LEGACY));
        } else {
            alert.setMessage(Html.fromHtml(
                    context.getString(R.string.locale_message)));
        }
        alert.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                listener.onSubmit();
            }
        });
        alert.show();
    }

    public interface AlertListener {
        void onSubmit();
    }

}
