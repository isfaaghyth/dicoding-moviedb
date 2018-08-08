package app.isfaaghyth.moviedb.network;

import app.isfaaghyth.moviedb.data.model.MovieRepository;
import app.isfaaghyth.moviedb.data.model.MovieTrailerRepository;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by isfaaghyth on 7/24/18.
 * github: @isfaaghyth
 */

public interface Routes {

    @GET("movie/{filtering}")
    Call<MovieRepository> getMovies(
            @Path("filtering") String filtering,
            @Query("language") String language,
            @Query("api_key") String apiKey);

    @GET("movie/{movie_id}/videos")
    Call<MovieTrailerRepository> getTrailerMovie(
            @Path("movie_id") String movieId,
            @Query("api_key") String apiKey);

    @GET("search/movie")
    Call<MovieRepository> getMovieByQuery(
            @Query("query") String keyword,
            @Query("language") String language,
            @Query("api_key") String apiKey);

}
