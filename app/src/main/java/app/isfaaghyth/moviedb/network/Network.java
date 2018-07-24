package app.isfaaghyth.moviedb.network;

import app.isfaaghyth.moviedb.BuildConfig;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by isfaaghyth on 7/24/18.
 * github: @isfaaghyth
 */

public class Network {

    public static Retrofit builder() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.MAIN_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

}
