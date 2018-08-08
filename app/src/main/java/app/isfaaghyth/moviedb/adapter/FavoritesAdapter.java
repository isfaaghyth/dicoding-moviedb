package app.isfaaghyth.moviedb.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import app.isfaaghyth.moviedb.R;
import app.isfaaghyth.moviedb.data.Movie;
import app.isfaaghyth.moviedb.data.local.DatabaseConstruct;
import app.isfaaghyth.moviedb.data.local.FavoritesColumn;
import app.isfaaghyth.moviedb.ui.detailmovie.DetailMovieActivity;
import app.isfaaghyth.moviedb.utils.Consts;
import app.isfaaghyth.moviedb.utils.CursorRecyclerViewAdapter;

import static android.provider.BaseColumns._ID;
import static app.isfaaghyth.moviedb.data.local.DatabaseConstruct.getColumnInt;
import static app.isfaaghyth.moviedb.data.local.DatabaseConstruct.getColumnLong;
import static app.isfaaghyth.moviedb.data.local.DatabaseConstruct.getColumnString;
/**
 * Created by isfaaghyth on 8/9/18.
 * github: @isfaaghyth
 */

public class FavoritesAdapter extends CursorRecyclerViewAdapter<FavoritesAdapter.FavoriteHolder> {

    public FavoritesAdapter(Context context, Cursor cursor) {
        super(context, cursor);
    }

    @Override public void onBindViewHolder(FavoriteHolder viewHolder, Cursor cursor) {
        cursor.moveToPosition(cursor.getPosition());
        viewHolder.setData(cursor);
    }

    @Override public FavoriteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FavoriteHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false));
    }

    @Override public int getItemCount() {
        return super.getItemCount();
    }

    class FavoriteHolder extends RecyclerView.ViewHolder {
        
        private CardView cardMovie;
        private ImageView imgPoster;
        private TextView txtMovieName, txtYear;

        FavoriteHolder(View itemView) {
            super(itemView);
            cardMovie = itemView.findViewById(R.id.card_movie);
            imgPoster = itemView.findViewById(R.id.img_poster);
            txtMovieName= itemView.findViewById(R.id.txt_movie_name);
            txtYear = itemView.findViewById(R.id.txt_year);
        }

        void setData(final Cursor cursor) {
            Glide.with(imgPoster.getContext())
                    .load(Consts.loadMovieBanner(getColumnString(cursor, FavoritesColumn.getPoster_path())))
                    .apply(new RequestOptions()
                            .placeholder(R.drawable.img_placeholder)
                            .centerCrop())
                    .into(imgPoster);
            txtMovieName.setText(getColumnString(cursor, FavoritesColumn.getTitle()));
            txtYear.setText(getColumnString(cursor, FavoritesColumn.getRelease_date()).split("-")[0]);

            cardMovie.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    int id = cursor.getInt(cursor.getColumnIndexOrThrow(FavoritesColumn._ID));
                    Intent detail = new Intent(v.getContext(), DetailMovieActivity.class);
                    detail.setData(Uri.parse(DatabaseConstruct.CONTENT_URI + "/" + id));
                    v.getContext().startActivity(detail);
                }
            });
        }
    }
    
}
