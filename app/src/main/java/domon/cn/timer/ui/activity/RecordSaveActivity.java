package domon.cn.timer.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import domon.cn.timer.App;
import domon.cn.timer.R;
import domon.cn.timer.data.Config;
import domon.cn.timer.data.RecordData;
import domon.cn.timer.utils.CommonUtils;

public class RecordSaveActivity extends AppCompatActivity {
    private String mRecordTime;
    private String mCategoryName;

    @Bind(R.id.save_tags_tv)
    TextView mTagsTv;
    @Bind(R.id.save_time_tv)
    TextView mTimeTv;
    @Bind(R.id.save_submit_iv)
    ImageView mSubmitIv;

    @OnClick(R.id.save_tags_tv)
    void onClickTags() {
        CategoriesActivity.actionStart(RecordSaveActivity.this);
    }

    @OnClick(R.id.save_submit_iv)
    void onClickSubmit() {
        Logger.i("Submit Times");
        RecordData recordData = new RecordData(
                Config.getUserImei(),
                Config.getCategoryName(),
                Config.getRecordTime(),
                getTodayDate()
        );

        App.liteOrm.save(recordData);

        finish();
    }

    @OnClick(R.id.save_close_iv)
    void onClickClose() {
        CommonUtils.showAlert(this);
    }


    public static void actionStart(Context context) {
        Intent i = new Intent(context, RecordSaveActivity.class);
        context.startActivity(i);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Config.getCategoryName() != null) {
            mTagsTv.setText(Config.getCategoryName());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_save);

        ButterKnife.bind(this);

        mRecordTime = CommonUtils.formatTime(Config.getRecordTime());
        if (Config.getCategoryName() != null) {
            mTagsTv.setText(Config.getCategoryName());
        }

        mTimeTv.setText(mRecordTime);
    }

    private String getTodayDate() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        return sf.format(date);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
