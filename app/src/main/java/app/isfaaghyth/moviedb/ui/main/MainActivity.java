package app.isfaaghyth.moviedb.ui.main;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;

import app.isfaaghyth.moviedb.R;
import app.isfaaghyth.moviedb.adapter.ViewPagerAdapter;
import app.isfaaghyth.moviedb.base.BaseActivity;
import app.isfaaghyth.moviedb.ui.main.fragment.popular.PopularFragment;
import app.isfaaghyth.moviedb.utils.CustomViewPager;
import butterknife.BindView;

public class MainActivity extends BaseActivity implements MainView {

    @BindView(R.id.vp_main) CustomViewPager viewPagerMain;
    @BindView(R.id.tab_main) TabLayout tabMain;

    private ViewPagerAdapter adapter;
    private MainRequest request;

    @Override public int contentView() {
        return R.layout.activity_main;
    }

    @Override public void onCreated() {
        request = new MainRequest(this);

        adapter = new ViewPagerAdapter(
                getSupportFragmentManager(),
                false);
        viewPagerMain.setPagingEnabled(false);
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

                return false;
            }

            @Override public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    void setupViewPager(ViewPager viewPager) {
        adapter.addFragment(new PopularFragment(), "Popular");
        viewPager.setAdapter(adapter);
    }

//    @Override public void onSuccess(MovieRepository result) {
//        if (result.getResults().size() > 0) {
//            if (result.getPage() == 1) movies.clear(); //selain page 1 di append
//            movies.addAll(result.getResults());
//            adapter.notifyDataSetChanged();
//        } else {
//            onInfo("Tidak ada movie.");
//        }
//    }

}
