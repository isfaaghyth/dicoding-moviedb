package app.isfaaghyth.moviedb.data;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

import app.isfaaghyth.moviedb.data.local.FavoritesColumn;

import static android.provider.BaseColumns._ID;
import static app.isfaaghyth.moviedb.data.local.DatabaseConstruct.getColumnInt;
import static app.isfaaghyth.moviedb.data.local.DatabaseConstruct.getColumnLong;
import static app.isfaaghyth.moviedb.data.local.DatabaseConstruct.getColumnString;

/**
 * Created by isfaaghyth on 7/24/18.
 * github: @isfaaghyth
 */

public class Movie implements Parcelable {
    private int id;
    private float vote_average;
    private int vote_count;
    private String title;
    private String poster_path;
    private String backdrop_path;
    private String overview;
    private String release_date;

    public Movie() {}

    protected Movie(Parcel in) {
        id = in.readInt();
        vote_average = in.readFloat();
        vote_count = in.readInt();
        title = in.readString();
        poster_path = in.readString();
        backdrop_path = in.readString();
        overview = in.readString();
        release_date = in.readString();
    }

    public Movie(Cursor cursor){
        this.id = getColumnInt(cursor, _ID);
        this.vote_average = getColumnLong(cursor, FavoritesColumn.getVote_average());
        this.vote_count = getColumnInt(cursor, FavoritesColumn.getVote_count());
        this.title = getColumnString(cursor, FavoritesColumn.getTitle());
        this.poster_path = getColumnString(cursor, FavoritesColumn.getPoster_path());
        this.backdrop_path = getColumnString(cursor, FavoritesColumn.getBackdrop_path());
        this.overview = getColumnString(cursor, FavoritesColumn.getOverview());
        this.release_date = getColumnString(cursor, FavoritesColumn.getRelease_date());
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public int getId() {
        return id;
    }

    public float getVote_average() {
        return vote_average;
    }

    public int getVote_count() {
        return vote_count;
    }

    public String getTitle() {
        return title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getOverview() {
        return overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String parcelMovie() {
        return new Gson().toJson(this);
    }

    @Override public int describeContents() {
        return 0;
    }

    @Override public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeFloat(vote_average);
        dest.writeInt(vote_count);
        dest.writeString(title);
        dest.writeString(poster_path);
        dest.writeString(backdrop_path);
        dest.writeString(overview);
        dest.writeString(release_date);
    }
}
