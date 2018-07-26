package app.isfaaghyth.moviedb.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.isfaaghyth.moviedb.utils.ProgressLoader;
import butterknife.ButterKnife;
import de.mateware.snacky.Snacky;

/**
 * Created by isfaaghyth on 7/26/18.
 * github: @isfaaghyth
 */

public abstract class BaseFragment extends Fragment implements BaseView {

    public abstract int contentView();
    public abstract void onCreated();

    private ProgressLoader loader;

    @Nullable @Override public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(contentView(), container, false);
    }

    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        onCreated();

        loader = new ProgressLoader(getContext());
    }

    @Override public void showLoader() {
        loader.show();
    }

    @Override public void hideLoader() {
        loader.hide();
    }

    protected void onError(int resId) {
        onError(getString(resId));
    }

    protected void onInfo(String message) {
        Snacky.builder()
                .setView(getView())
                .setText(message)
                .setDuration(Snacky.LENGTH_SHORT)
                .info()
                .show();
    }

    protected void onError(String message) {
        Snacky.builder()
                .setView(getView())
                .setText(message)
                .setDuration(Snacky.LENGTH_SHORT)
                .error()
                .show();
    }

}
