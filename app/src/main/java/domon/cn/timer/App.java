package domon.cn.timer;

import android.app.Application;

import com.litesuits.orm.LiteOrm;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

import domon.cn.timer.utils.PreferencesUtils;

/**
 * Created by Domon on 2017/3/13.
 */

public class App extends Application {
    public static LiteOrm liteOrm;

    @Override
    public void onCreate() {
        super.onCreate();

        if (liteOrm == null) {
            liteOrm = LiteOrm.newCascadeInstance(this, "test.db");
        }

        Logger.init("Timer")
                .methodCount(3)
                .hideThreadInfo()
                .logLevel(LogLevel.FULL)
                .methodOffset(2);

        PreferencesUtils.initPreferences(this);
    }
}
