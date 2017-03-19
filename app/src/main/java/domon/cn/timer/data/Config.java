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

    public static String getUserImei() {
        return PreferencesUtils.getStringValue("user_imei");
    }

    public static void setUserImei(String imei) {
        PreferencesUtils.setStringValue("user_imei", imei);
    }

    public static int getCategoryId() {
        return PreferencesUtils.getIntegerValue("category_id");
    }

    public static void setCategoryId(int categoryId) {
        PreferencesUtils.setIntegerValue("category_id", categoryId);
    }

    public static String getCategoryName() {
        return PreferencesUtils.getStringValue("category_name");
    }

    public static void setCategoryName(String categoryName) {
        PreferencesUtils.setStringValue("category_name", categoryName);
    }

    public static String getRecordTime() {
        return PreferencesUtils.getStringValue("record_time");
    }

    public static void setRecordTime(String recordTime) {
        PreferencesUtils.setStringValue("record_time", recordTime);
    }

}
