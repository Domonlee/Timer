package domon.cn.timer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RecordActivity extends AppCompatActivity {
    @Bind(R.id.timer_tv)
    TextView mTimerTv;

    private Timer mTimer;
    private TimerTask mTimerTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        ButterKnife.bind(this);

        initTimer();
    }

    private void initTimer() {
        mTimer = new Timer();

        mTimerTask = new TimerTask() {
            int count = 0;

            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTimerTv.setText(getTime(count++));
                    }
                });
            }
        };
        mTimer.schedule(mTimerTask, 0, 1000);
    }

    private String getTime(int count) {
        int hour = count / 3600;
        int min = count % 3600 / 60;
        int second = count % 60;
        if (hour == 0) {
            return String.format(Locale.CHINA, "%02d:%02d", min, second);
        } else {
            return String.format(Locale.CHINA, "%02d:%02d:%02d", hour, min, second);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
