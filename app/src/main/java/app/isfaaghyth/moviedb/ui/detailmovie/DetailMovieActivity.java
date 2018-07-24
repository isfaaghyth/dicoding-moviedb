package app.isfaaghyth.moviedb.ui.detailmovie;

import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import app.isfaaghyth.moviedb.R;
import app.isfaaghyth.moviedb.base.BaseActivity;
import app.isfaaghyth.moviedb.data.Movie;
import app.isfaaghyth.moviedb.utils.Consts;
import butterknife.BindView;

/**
 * Created by isfaaghyth on 7/24/18.
 * github: @isfaaghyth
 */

public class DetailMovieActivity extends BaseActivity {

    @BindView(R.id.img_banner) ImageView imgBanner;
    @BindView(R.id.img_poster) ImageView imgPoster;
    @BindView(R.id.txt_movie_name) TextView txtMovieName;
    @BindView(R.id.txt_year) TextView txtYear;
    @BindView(R.id.txt_votes) TextView txtVotes;
    @BindView(R.id.txt_votes_count) TextView txtVotesCount;
    @BindView(R.id.txt_ratings) TextView txtRatings;
    @BindView(R.id.txt_overview) TextView txtOverview;

    @Override public int contentView() {
        return R.layout.activity_detail_movie;
    }

    @Override public void onCreated() {
        showBackBar();
        loadMovieDetail(intentValue("movie"));
    }

    private void loadMovieDetail(String result) {
        Movie movie = new Gson().fromJson(result, Movie.class);
        Glide.with(this)
                .load(Consts.loadMovieBanner(movie.getBackdrop_path()))
                .into(imgBanner);
        Glide.with(this)
                .load(Consts.loadMovieBanner(movie.getPoster_path()))
                .into(imgPoster);

        txtMovieName.setText(movie.getTitle());
        txtYear.setText(movie.getRelease_date());
        txtVotesCount.setText(String.valueOf(movie.getVote_count()));
        txtVotes.setText(String.valueOf(movie.getVote_average()));
        txtRatings.setText(String.valueOf(movie.getPopularity()));
        txtOverview.setText(movie.getOverview());
    }

}
