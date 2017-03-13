package domon.cn.timer;

import android.app.Application;

import com.litesuits.orm.LiteOrm;

/**
 * Created by Domon on 2017/3/13.
 */

public class App extends Application {
    static LiteOrm liteOrm;

    @Override
    public void onCreate() {
        super.onCreate();
        if (liteOrm == null) {
            liteOrm = LiteOrm.newCascadeInstance(this, "test.db");
        }

        liteOrm.setDebugged(true);
    }
}
