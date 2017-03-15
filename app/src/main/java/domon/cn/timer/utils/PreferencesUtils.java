package domon.cn.timer.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.orhanobut.logger.Logger;

/**
 * Created by Domon on 2017/3/15.
 */

public class PreferencesUtils {
    public static final String preferences_name = "Preferences_utils";
    private static SharedPreferences mSp;

    public static void initPreferences(Context context) {
        try {
            if (mSp != null) {
                return;
            }
            mSp = context.getSharedPreferences(preferences_name, Context.MODE_WORLD_READABLE);
        } catch (Exception e) {
            Logger.e(e.toString());
        }
    }

    public static void delete(String name) {
        try {
            SharedPreferences.Editor editor = mSp.edit();
            editor.remove(name);
            editor.commit();
        } catch (Exception e) {
        }
    }

    public static synchronized String getStringValue(String key) {
        try {
            if (null == mSp)
                return null;
            else
                return mSp.getString(key, null);
        } catch (Exception e) {
            return null;
        }
    }

    public static synchronized Integer getIntegerValue(String key) {
        try {
            if (null == mSp)
                return -1;
            else
                return mSp.getInt(key, -1);
        } catch (Exception e) {
            return -1;
        }
    }

    public static synchronized long getLongValue(String key) {
        try {
            if (null == mSp)
                return -1;
            else
                return mSp.getLong(key, -1);
        } catch (Exception e) {
            return -1;
        }
    }

    public static synchronized Integer getIntegerValueDefault(String key) {
        try {
            if (null == mSp)
                return -1;
            else
                return mSp.getInt(key, -1);
        } catch (Exception e) {
            return -1;
        }
    }

    public static synchronized void setStringValue(String key, String value) {
        try {
            if (null != mSp) {
                SharedPreferences.Editor editor = mSp.edit();
                editor.putString(key, value);
                editor.commit();
            }
        } catch (Exception e) {
        }
    }

    public static synchronized void setIntegerValue(String key, Integer value) {
        try {
            if (null != mSp) {
                SharedPreferences.Editor editor = mSp.edit();
                editor.putInt(key, value);
                editor.commit();
            }
        } catch (Exception e) {
        }
    }

    public static synchronized void setLongValue(String key, long value) {
        try {
            if (null != mSp) {
                SharedPreferences.Editor editor = mSp.edit();
                editor.putLong(key, value);
                editor.commit();
            }
        } catch (Exception e) {
        }
    }

    public static synchronized void clearAllConfig() {
        try {
            if (null != mSp) {
                SharedPreferences.Editor editor = mSp.edit();
                editor.clear();
                editor.commit();
            }
        } catch (Exception e) {
        }
    }

    public static void destory() {
        mSp = null;
        Logger.i("hesine preferences xml destory.");
    }
}
