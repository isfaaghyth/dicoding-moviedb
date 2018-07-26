package app.isfaaghyth.moviedb.ui.main.fragment.nowplaying;

import android.view.View;

import app.isfaaghyth.moviedb.R;
import app.isfaaghyth.moviedb.base.BaseFragment;

/**
 * Created by isfaaghyth on 7/26/18.
 * github: @isfaaghyth
 */

public class NowPlayingFragment extends BaseFragment implements NowPlayingView {

    @Override public int contentView() {
        return R.layout.fragment_now_playing;
    }

    @Override public void onCreated(View view) {
    }

}
