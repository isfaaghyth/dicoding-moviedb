package app.isfaaghyth.moviedb.ui.main;

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

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainHolder> {

    private List<Movie> movies;

    MainAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    @Override public MainHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false));
    }

    @Override public void onBindViewHolder(MainHolder holder, final int position) {
        Glide.with(holder.imgPoster.getContext())
                .load(Consts.loadMovieBanner(movies.get(position).getPoster_path()))
                .apply(new RequestOptions()
                            .placeholder(R.drawable.img_placeholder)
                            .centerCrop())
                .into(holder.imgPoster);
        holder.txtMovieName.setText(movies.get(position).getTitle());
        holder.txtYear.setText(movies.get(position).getRelease_date().split("-")[0]);

        holder.cardMovie.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent detail = new Intent(v.getContext(), DetailMovieActivity.class);
                detail.putExtra("movie", movies.get(position).parcelMovie(movies.get(position)));
                v.getContext().startActivity(detail);
            }
        });
    }

    @Override public int getItemCount() {
        return movies.size();
    }

    class MainHolder extends RecyclerView.ViewHolder {

        CardView cardMovie;
        ImageView imgPoster;
        TextView txtMovieName, txtYear;

        MainHolder(View itemView) {
            super(itemView);
            cardMovie = itemView.findViewById(R.id.card_movie);
            imgPoster = itemView.findViewById(R.id.img_poster);
            txtMovieName= itemView.findViewById(R.id.txt_movie_name);
            txtYear = itemView.findViewById(R.id.txt_year);
        }
    }

}
