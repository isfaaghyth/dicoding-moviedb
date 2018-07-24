package app.isfaaghyth.moviedb.network;

import app.isfaaghyth.moviedb.data.MovieRepository;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by isfaaghyth on 7/24/18.
 * github: @isfaaghyth
 */

public interface Routes {

    @GET("movie/popular")
    Call<MovieRepository> getPopularMovies(@Query("api_key") String apiKey);

}
