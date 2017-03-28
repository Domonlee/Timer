package domon.cn.timer.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import domon.cn.timer.R;
import domon.cn.timer.data.Config;
import domon.cn.timer.utils.CommonUtils;

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
        Toast.makeText(this, CommonUtils.formatTime(mCount), Toast.LENGTH_LONG).show();
        Config.setRecordTime(mCount);
        RecordSaveActivity.actionStart(RecordActivity.this);
        stopTimer();
        finish();
    }


    @OnClick(R.id.record_cancel_iv)
    void onClickCancel() {
        CommonUtils.showAlert(this);
        onClickStart();
    }

    public static void actionStart(Context context) {
        Intent i = new Intent(context, RecordActivity.class);
        context.startActivity(i);
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
                                mTimerTv.setText(CommonUtils.formatTime(mCount));
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        stopTimer();
    }

    @Override
    public void onBackPressed() {
        CommonUtils.showAlert(this);
    }
}
