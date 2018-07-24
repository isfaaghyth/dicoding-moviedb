package app.isfaaghyth.moviedb.ui.detailmovie;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import app.isfaaghyth.moviedb.R;
import app.isfaaghyth.moviedb.base.BaseActivity;
import app.isfaaghyth.moviedb.data.Movie;
import app.isfaaghyth.moviedb.data.MovieTrailerRepository;
import app.isfaaghyth.moviedb.utils.Consts;
import butterknife.BindView;

/**
 * Created by isfaaghyth on 7/24/18.
 * github: @isfaaghyth
 */

public class DetailMovieActivity extends BaseActivity implements DetailMovieView<MovieTrailerRepository> {

    @BindView(R.id.img_banner) ImageView imgBanner;
    @BindView(R.id.img_poster) ImageView imgPoster;
    @BindView(R.id.txt_movie_name) TextView txtMovieName;
    @BindView(R.id.txt_year) TextView txtYear;
    @BindView(R.id.txt_votes) TextView txtVotes;
    @BindView(R.id.txt_ratings) TextView txtRatings;
    @BindView(R.id.txt_overview) TextView txtOverview;

    private DetailMoviewRequest request;
    private Movie movie;

    @Override public int contentView() {
        return R.layout.activity_detail_movie;
    }

    @Override public void onCreated() {
        showBackBar();
        request = new DetailMoviewRequest(this);
        loadMovieDetail(intentValue("movie"));

        //button callbak
        onPlayTrailer();
        onShareMovie();
    }

    private void loadMovieDetail(String result) {
        movie = new Gson().fromJson(result, Movie.class);
        Glide.with(this)
                .load(Consts.loadMovieBanner(movie.getBackdrop_path()))
                .into(imgBanner);
        Glide.with(this)
                .load(Consts.loadMovieBanner(movie.getPoster_path()))
                .into(imgPoster);

        txtMovieName.setText(movie.getTitle());
        txtYear.setText(movie.getRelease_date());
        txtVotes.setText(String.valueOf(movie.getVote_count()));
        txtRatings.setText(String.valueOf(movie.getVote_average()));
        txtOverview.setText(movie.getOverview());
    }

    private void onPlayTrailer() {
        findViewById(R.id.btn_play).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                request.movieTrailer(movie.getId());
            }
        });
    }

    private void onShareMovie() {
        findViewById(R.id.btn_share).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {

            }
        });
    }

    @Override public Context context() {
        return getApplicationContext();
    }

    @Override public void onSuccess(MovieTrailerRepository result) {
        Toast.makeText(this, result.getResults().get(0).getKey(), Toast.LENGTH_LONG).show();
    }

    @Override public void onError(String message) {
        super.onError(message);
    }

}
