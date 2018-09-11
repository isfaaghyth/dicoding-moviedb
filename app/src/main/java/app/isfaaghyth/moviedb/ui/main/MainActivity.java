package app.isfaaghyth.moviedb.ui.main;

import android.content.Intent;
import android.provider.Settings;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import app.isfaaghyth.moviedb.R;
import app.isfaaghyth.moviedb.adapter.ViewPagerAdapter;
import app.isfaaghyth.moviedb.base.BaseActivity;
import app.isfaaghyth.moviedb.ui.favorite.FavoriteActivity;
import app.isfaaghyth.moviedb.ui.main.dialog.LocaleDialog;
import app.isfaaghyth.moviedb.ui.main.fragment.nowplaying.NowPlayingFragment;
import app.isfaaghyth.moviedb.ui.main.fragment.popular.PopularFragment;
import app.isfaaghyth.moviedb.ui.main.fragment.upcoming.UpcomingFragment;
import app.isfaaghyth.moviedb.ui.searchmovie.SearchActivity;
import app.isfaaghyth.moviedb.ui.settings.SettingActivity;
import app.isfaaghyth.moviedb.utils.CustomViewPager;
import butterknife.BindView;

public class MainActivity extends BaseActivity implements MainView {

    @BindView(R.id.vp_main) CustomViewPager viewPagerMain;
    @BindView(R.id.tab_main) TabLayout tabMain;

    private ViewPagerAdapter adapter;

    @Override public int contentView() {
        return R.layout.activity_main;
    }

    @Override public void onCreated() {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerMain.setPagingEnabled(true);
        viewPagerMain.setOffscreenPageLimit(4);
        setupViewPager(viewPagerMain);
        tabMain.setupWithViewPager(viewPagerMain);
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_movie_filter, menu);

        SearchView searchMovieView = (SearchView) menu.findItem(R.id.mn_search).getActionView();
        searchMovieView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override public boolean onQueryTextSubmit(String query) {
                Intent search = new Intent(MainActivity.this, SearchActivity.class);
                search.putExtra("keyword", query);
                startActivity(search);
                return false;
            }

            @Override public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mn_settings:
                startActivity(new Intent(this, SettingActivity.class));
                break;
            case R.id.mn_favorites:
                startActivity(new Intent(MainActivity.this, FavoriteActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    void setupViewPager(ViewPager viewPager) {
        adapter.addFragment(new PopularFragment(), getString(R.string.popular));
        adapter.addFragment(new NowPlayingFragment(), getString(R.string.now_playing));
        adapter.addFragment(new UpcomingFragment(), getString(R.string.upcoming));
        viewPager.setAdapter(adapter);
    }

}
