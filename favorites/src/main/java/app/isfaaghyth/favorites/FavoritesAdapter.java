package app.isfaaghyth.favorites;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by isfaaghyth on 8/11/18.
 * github: @isfaaghyth
 */

public class FavoritesAdapter extends CursorAdapter {

    public FavoritesAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
    }

    @Override public void bindView(View view, Context context, Cursor cursor) {
        if (cursor != null) {
            ImageView imgPoster = view.findViewById(R.id.img_poster);
            TextView txtMovieName = view.findViewById(R.id.txt_movie_name);
            TextView txtYear = view.findViewById(R.id.txt_year);

            Glide.with(context)
                    .load(cursor.getString(cursor.getColumnIndex("poster_path")))
                    .centerCrop()
                    .into(imgPoster);

            txtMovieName.setText(cursor.getString(cursor.getColumnIndex("title")));
            txtYear.setText(cursor.getString(cursor.getColumnIndex("release_date")));
        }
    }

    @Override public Cursor getCursor() {
        return super.getCursor();
    }

}
