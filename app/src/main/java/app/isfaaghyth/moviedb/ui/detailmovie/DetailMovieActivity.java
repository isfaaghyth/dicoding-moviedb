package app.isfaaghyth.moviedb.ui.detailmovie;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import app.isfaaghyth.moviedb.BuildConfig;
import app.isfaaghyth.moviedb.R;
import app.isfaaghyth.moviedb.base.BaseActivity;
import app.isfaaghyth.moviedb.data.Movie;
import app.isfaaghyth.moviedb.data.MovieTrailerRepository;
import app.isfaaghyth.moviedb.data.local.FavoriteManager;
import app.isfaaghyth.moviedb.utils.Consts;
import app.isfaaghyth.moviedb.utils.ShareIntentUtil;
import butterknife.BindView;

/**
 * Created by isfaaghyth on 7/24/18.
 * github: @isfaaghyth
 */

public class DetailMovieActivity extends BaseActivity implements DetailMovieView {

    @BindView(R.id.img_banner) ImageView imgBanner;
    @BindView(R.id.img_poster) ImageView imgPoster;
    @BindView(R.id.txt_movie_name) TextView txtMovieName;
    @BindView(R.id.txt_year) TextView txtYear;
    @BindView(R.id.txt_votes) TextView txtVotes;
    @BindView(R.id.txt_ratings) TextView txtRatings;
    @BindView(R.id.txt_overview) TextView txtOverview;

    private DetailMoviewRequest request;
    private Movie movie;
    private Menu menu;

    private FavoriteManager favoriteManager;

    private boolean isFavorited = false;

    @Override public int contentView() {
        return R.layout.activity_detail_movie;
    }

    @Override public void onCreated() {
        super.showBackBar();
        request = new DetailMoviewRequest(this);
        Uri uri = getIntent().getData();

        favoriteManager = new FavoriteManager(this);
        favoriteManager.open();

        if (uri != null) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                movie = new Movie(cursor);
                cursor.close();
            } else {
                onBackPressed();
                finish();
            }
        } else {
            movie = new Gson().fromJson(intentValue("movie"), Movie.class);
        }

        loadMovieDetail(movie);

        //button callback
        onPlayTrailer();
        onShareMovie();
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        favoriteManager.close();
    }

    private void loadMovieDetail(Movie movie) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(movie.getTitle());
        }

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

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_favorites, menu);
        this.menu = menu;

        int favCount = favoriteManager.queryByIdProvider(
                String.valueOf(movie.getId())).getCount();

        if (favCount > 0) {
            menu.getItem(0).setIcon(R.mipmap.ic_fav);
            isFavorited = true;
        } else {
            menu.getItem(0).setIcon(R.mipmap.ic_unfav);
            isFavorited = false;
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mn_fav:
                if (!isFavorited) {
                    int favCount = favoriteManager.queryByIdProvider(
                            String.valueOf(movie.getId())).getCount();
                    if (favCount > 0) {
                        favoriteManager.updateProvider(String.valueOf(movie.getId()), movie);
                    } else {
                        favoriteManager.insert(movie);
                    }
                    menu.getItem(0).setIcon(R.mipmap.ic_fav);
                    isFavorited = true;
                } else {
                    favoriteManager.delete(movie.getId());
                    menu.getItem(0).setIcon(R.mipmap.ic_unfav);
                    isFavorited = false;
                }
                break;
        }
        return super.onOptionsItemSelected(item);
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
                ShareIntentUtil.shareMovie(v.getContext(),
                        movie.getTitle(),
                        String.valueOf(movie.getVote_average()),
                        movie.getOverview());
            }
        });
    }

    @Override public void onSuccess(MovieTrailerRepository result) {
        String key = result.getResults().get(0).getKey(); //ambil yang paling pertama aja
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(BuildConfig.YT_URL + key));
        getApplicationContext().startActivity(i);
    }

    @Override public void onError(String message) {
        super.onError(message);
    }

}
