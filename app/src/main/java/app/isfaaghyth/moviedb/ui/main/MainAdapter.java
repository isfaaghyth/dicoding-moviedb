package app.isfaaghyth.moviedb.ui.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import app.isfaaghyth.moviedb.R;
import app.isfaaghyth.moviedb.data.Movie;
import app.isfaaghyth.moviedb.utils.Consts;

/**
 * Created by isfaaghyth on 7/25/18.
 * github: @isfaaghyth
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainHolder> {

    private List<Movie> movies;

    public MainAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    @Override public MainHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false));
    }

    @Override public void onBindViewHolder(MainHolder holder, int position) {
        Glide.with(holder.imgBanner.getContext())
                .load(Consts.loadMovieBanner(movies.get(position).getBackdrop_path()))
                .into(holder.imgBanner);
        holder.txtMovieName.setText(movies.get(position).getTitle());
        holder.txtYear.setText(movies.get(position).getRelease_date().split("-")[0]);
    }

    @Override public int getItemCount() {
        return movies.size();
    }

    class MainHolder extends RecyclerView.ViewHolder {

        ImageView imgBanner;
        TextView txtMovieName, txtYear;

        MainHolder(View itemView) {
            super(itemView);
            imgBanner = itemView.findViewById(R.id.img_banner);
            txtMovieName= itemView.findViewById(R.id.txt_movie_name);
            txtYear = itemView.findViewById(R.id.txt_year);
        }
    }

}
