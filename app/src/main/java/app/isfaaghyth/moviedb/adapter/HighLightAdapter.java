package app.isfaaghyth.moviedb.adapter;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import app.isfaaghyth.moviedb.R;
import app.isfaaghyth.moviedb.data.Movie;
import app.isfaaghyth.moviedb.ui.detailmovie.DetailMovieActivity;
import app.isfaaghyth.moviedb.utils.Consts;

/**
 * Created by isfaaghyth on 7/27/18.
 * github: @isfaaghyth
 */

public class HighLightAdapter extends RecyclerView.Adapter<HighLightAdapter.HighLightHolder> {

    private List<Movie> movies;

    public HighLightAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    @Override public HighLightHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View card = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_highlight, parent, false);
        RelativeLayout cardRoot = card.findViewById(R.id.card_root);
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        int width = displayMetrics.widthPixels;
        ViewGroup.LayoutParams params = cardRoot.getLayoutParams();
        params.width = ((width * 90) / 100);
        cardRoot.setLayoutParams(params);
        return new HighLightHolder(card);
    }

    @Override public void onBindViewHolder(HighLightHolder holder, final int position) {
        holder.setData(movies.get(position));
    }

    @Override public int getItemCount() {
        return movies.size();
    }

    class HighLightHolder extends RecyclerView.ViewHolder {

        private CardView cardMovie;
        private ImageView imgPoster;
        private TextView txtMovieName, txtYear;

        HighLightHolder(View itemView) {
            super(itemView);
            cardMovie = itemView.findViewById(R.id.card_movie);
            imgPoster = itemView.findViewById(R.id.img_poster);
            txtMovieName= itemView.findViewById(R.id.txt_movie_name);
            txtYear = itemView.findViewById(R.id.txt_year);
        }

        void setData(final Movie movie) {
            Glide.with(imgPoster.getContext())
                    .load(Consts.loadMovieBanner(movie.getBackdrop_path()))
                    .apply(new RequestOptions()
                            .placeholder(R.drawable.img_placeholder)
                            .centerCrop())
                    .into(imgPoster);
            txtMovieName.setText(movie.getTitle());
            txtYear.setText(movie.getRelease_date().split("-")[0]);

            cardMovie.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    Intent detail = new Intent(v.getContext(), DetailMovieActivity.class);
                    detail.putExtra("movie", movie.parcelMovie());
                    v.getContext().startActivity(detail);
                }
            });
        }
    }

}
