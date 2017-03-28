package domon.cn.timer.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

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

    public static void showAlert(final Context context) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("确认退出嘛？");
        builder.setMessage("您打算放弃本次记录嘛？");
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ((Activity) context).finish();
            }
        });

        builder.show();
    }
}
