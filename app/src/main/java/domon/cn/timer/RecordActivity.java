package domon.cn.timer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecordActivity extends AppCompatActivity {
    public static final int RECORD_STATE_INIT = 0;
    public static final int RECORD_STATE_RESTART = 1;
    public static final int RECORD_STATE_PAUSE = 2;
    public static final int RECORD_STATE_STOP = 3;

    @Bind(R.id.timer_tv)
    TextView mTimerTv;
    @Bind(R.id.record_start_iv)
    ImageView mRecordStartIv;
    @Bind(R.id.record_cancel_iv)
    ImageView mRecordCancelIv;

    @OnClick(R.id.record_start_iv)
        // TODO: 2017/3/8 这里有问题，不应该是三个状态，使用两个状态也是OK，其次注意count数字的保存
    void onClickStart() {
        Logger.e("mRecordState = " + mRecordState);
        switch (mRecordState) {
            case RECORD_STATE_INIT:
                mTimer = new Timer();
                mTimer.schedule(mTimerTask, 0, 1000);
                mRecordStartIv.setImageResource(R.mipmap.pause);
                mRecordState = RECORD_STATE_PAUSE;
                break;
            case RECORD_STATE_PAUSE:
                mTimer.cancel();
                mRecordState = RECORD_STATE_RESTART;
                break;
            case RECORD_STATE_RESTART:
                mTimer = new Timer();
                mTimer.schedule(mTimerTask,0,1000);
                mRecordState = RECORD_STATE_INIT;
                break;
            default:
                break;
        }
    }

    @OnClick(R.id.record_cancel_iv)
    void onClickCancel() {
        mTimerTask.cancel();
        mRecordStartIv.setImageResource(R.mipmap.start);
        mTimerTv.setText("00:00");

        mRecordState = RECORD_STATE_RESTART;
    }

    private Timer mTimer;
    private TimerTask mTimerTask;
    private int mRecordState = 0;
    private int mCount = 0;

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
                        mCount = count;
                    }
                });
            }
        };
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
