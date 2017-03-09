package domon.cn.timer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.ToDoubleBiFunction;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 记录时间界面
 *
 * @author Domon
 */

public class RecordActivity extends AppCompatActivity {
    private Timer mTimer;
    private TimerTask mTimerTask;
    private int mCount = 0;
    private boolean isPause = false;

    @Bind(R.id.timer_tv)
    TextView mTimerTv;
    @Bind(R.id.record_start_iv)
    ImageView mRecordStartIv;
    @Bind(R.id.record_cancel_iv)
    ImageView mRecordCancelIv;
    @Bind(R.id.record_finish_iv)
    ImageView mRecordFinishIv;

    @OnClick(R.id.record_start_iv)
    void onClickStart() {
        if (isPause) {
            Logger.i("Resume");
            mRecordStartIv.setImageResource(R.mipmap.pause);
        } else {
            Logger.i("Pause");
            mRecordStartIv.setImageResource(R.mipmap.start);
        }

        isPause = !isPause;
    }

    @OnClick(R.id.record_finish_iv)
    void onClickFinish() {
        // TODO: 2017/3/9 添加跳转,数据需要传递到下一个界面
        Logger.d(mCount);
        Toast.makeText(this, getTime(mCount), Toast.LENGTH_LONG).show();
        stopTimer();
    }


    @OnClick(R.id.record_cancel_iv)
    void onClickCancel() {
        stopTimer();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        ButterKnife.bind(this);

        startTimer();
    }

    private void startTimer() {
        if (mTimer == null) {
            mTimer = new Timer();
        }

        if (mTimerTask == null) {
            mTimerTask = new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (!isPause) {
                                mCount++;
                                mTimerTv.setText(getTime(mCount));
                            }
                        }
                    });
                }
            };
        }

        if (mTimer != null && mTimerTask != null) {
            mRecordStartIv.setImageResource(R.mipmap.pause);
            mTimer.schedule(mTimerTask, 0, 1000);
        }
    }

    private void stopTimer() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }

        if (mTimerTask != null) {
            mTimerTask.cancel();
            mTimerTask = null;
        }
        mCount = 0;
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
        stopTimer();
    }
}
