package domon.cn.timer.utils;

import java.util.Locale;

/**
 * Created by Domon on 2017/3/27.
 */

public class CommonUtils {
    public static String formatTime(int count) {
        int hour = count / 3600;
        int min = count % 3600 / 60;
        int second = count % 60;
        if (hour == 0) {
            return String.format(Locale.CHINA, "%02d:%02d", min, second);
        } else {
            return String.format(Locale.CHINA, "%02d:%02d:%02d", hour, min, second);
        }
    }
}
