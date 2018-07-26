package app.isfaaghyth.moviedb.adapter;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import app.isfaaghyth.moviedb.R;
import app.isfaaghyth.moviedb.data.Movie;
import app.isfaaghyth.moviedb.ui.detailmovie.DetailMovieActivity;
import app.isfaaghyth.moviedb.utils.Consts;

/**
 * Created by isfaaghyth on 7/25/18.
 * github: @isfaaghyth
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MainHolder> {

    private List<Movie> movies;

    public MovieAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    @Override public MainHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false));
    }

    @Override public void onBindViewHolder(MainHolder holder, final int position) {
        holder.setData(movies.get(position));
    }

    @Override public int getItemCount() {
        return movies.size();
    }

    class MainHolder extends RecyclerView.ViewHolder {

        private CardView cardMovie;
        private ImageView imgPoster;
        private TextView txtMovieName, txtYear;

        MainHolder(View itemView) {
            super(itemView);
            cardMovie = itemView.findViewById(R.id.card_movie);
            imgPoster = itemView.findViewById(R.id.img_poster);
            txtMovieName= itemView.findViewById(R.id.txt_movie_name);
            txtYear = itemView.findViewById(R.id.txt_year);
        }

        void setData(final Movie movie) {
            Glide.with(imgPoster.getContext())
                    .load(Consts.loadMovieBanner(movie.getPoster_path()))
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
