package app.isfaaghyth.moviedb.ui.settings;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.view.MenuItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import app.isfaaghyth.moviedb.BuildConfig;
import app.isfaaghyth.moviedb.R;
import app.isfaaghyth.moviedb.base.BaseRequest;
import app.isfaaghyth.moviedb.data.model.Movie;
import app.isfaaghyth.moviedb.data.model.MovieRepository;
import app.isfaaghyth.moviedb.services.notification.MovieDailyReceiver;
import app.isfaaghyth.moviedb.services.notification.MovieUpcomingReceiver;
import app.isfaaghyth.moviedb.utils.Consts;
import app.isfaaghyth.moviedb.utils.ProgressLoader;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by isfaaghyth on 9/12/18.
 * github: @isfaaghyth
 */

public class SettingActivity extends AppPreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new MainPreferenceFragment()).commit();
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public static class MainPreferenceFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener{

        private SwitchPreference switchReminder;
        private SwitchPreference switchToday;

        private MovieDailyReceiver dailyReceiver = new MovieDailyReceiver();
        private MovieUpcomingReceiver upcomingReceiver = new MovieUpcomingReceiver();

        private BaseRequest request;

        @Override
        public void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_main);

            request = new BaseRequest();
            request.initialize();

            switchReminder = (SwitchPreference) findPreference(getString(R.string.key_today_reminder));
            switchReminder.setOnPreferenceChangeListener(this);
            switchToday = (SwitchPreference) findPreference(getString(R.string.key_release_reminder));
            switchToday.setOnPreferenceChangeListener(this);

            Preference myPref = findPreference(getString(R.string.key_lang));
            myPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                public boolean onPreferenceClick(Preference preference) {
                    startActivity(new Intent(Settings.ACTION_LOCALE_SETTINGS));
                    return true;
                }
            });

        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            String key = preference.getKey();
            boolean configurationValues = (boolean) newValue;

            if(key.equals(getString(R.string.key_today_reminder))){
                if(configurationValues){
                    dailyReceiver.setDailyAlarm(getActivity());
                }else{
                    dailyReceiver.cancelDailyAlarm(getActivity());
                }
            } else {
                if(configurationValues){
                    setNewReleaseAlarm();
                }else{
                    upcomingReceiver.cancelUpcomingAlarm(getActivity());
                }
            }

            return true;
        }

        private void setNewReleaseAlarm() {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Date date = new Date();
            final String dateNow = dateFormat.format(date);
            request.getRequest().getMovies(Consts.UPCOMING,
                    Locale.getDefault().toString(), BuildConfig.API_KEY)
                    .enqueue(new Callback<MovieRepository>() {
                        @Override public void onResponse(@NonNull Call<MovieRepository> call,
                                                         @NonNull Response<MovieRepository> response) {
                            List<Movie> newMoviesList = new ArrayList<>();
                            List<Movie> movies = response.body().getResults();
                            for (Movie movie: movies) {
                                if (movie.getRelease_date().equals(dateNow)) {
                                    newMoviesList.add(movie);
                                }
                            }
                            upcomingReceiver.setUpcomingAlarm(getActivity(), newMoviesList);
                        }

                        @Override public void onFailure(@NonNull Call<MovieRepository> call,
                                                        @NonNull Throwable t) {

                        }
                    });
        }

    }

}
