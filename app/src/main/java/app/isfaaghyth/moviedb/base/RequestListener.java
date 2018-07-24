package app.isfaaghyth.moviedb.base;

/**
 * Created by isfaaghyth on 7/24/18.
 * github: @isfaaghyth
 */

public interface RequestListener<M> {
    void onSuccess(M result);
    void onError(String message);
}
