package app.isfaaghyth.moviedb.utils;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import app.isfaaghyth.moviedb.R;

/**
 * Created by isfaaghyth on 7/25/18.
 * github: @isfaaghyth
 */

public class ProgressLoader {

    private AlertDialog alert;

    public ProgressLoader(Context context) {
        alert = new AlertDialog.Builder(context).create();
        View viewDialog = LayoutInflater.from(context).inflate(R.layout.dialog_progress, null);
        alert.setView(viewDialog);
        alert.setCancelable(false);
    }

    public void show() {
        alert.show();
    }

    public void hide() {
        alert.dismiss();
    }

}
