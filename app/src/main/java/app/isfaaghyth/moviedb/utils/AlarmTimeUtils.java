package app.isfaaghyth.moviedb.utils;

import java.util.Calendar;

/**
 * Created by isfaaghyth on 9/12/18.
 * github: @isfaaghyth
 */

public class AlarmTimeUtils {

    public static Calendar time(int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar;
    }

}
