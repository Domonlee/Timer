package domon.cn.timer.data;

import domon.cn.timer.utils.PreferencesUtils;

/**
 * Created by Domon on 2017/3/15.
 */

public class Config {

    public static int getUserId() {
        return PreferencesUtils.getIntegerValue("user_id");
    }

    public static void setUserId(int value) {
        PreferencesUtils.setIntegerValue("user_id", value);
    }

}
