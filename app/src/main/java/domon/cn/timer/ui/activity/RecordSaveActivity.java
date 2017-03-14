package domon.cn.timer.ui.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import domon.cn.timer.R;

public class RecordSaveActivity extends AppCompatActivity {
    private String mRecordTime;

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
        finish();
    }

    @OnClick(R.id.save_close_iv)
    void onClickClose() {
        showAlert();
    }


    public static void actionStart(Context context, String recordTimeStr) {
        Intent i = new Intent(context, RecordSaveActivity.class);
        i.putExtra("RecordTime", recordTimeStr);
        context.startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_save);

        ButterKnife.bind(this);

        Intent i = getIntent();
        mRecordTime = i.getStringExtra("RecordTime");

        mTimeTv.setText(mRecordTime);
    }

    private void showAlert() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("确认退出嘛？");
        builder.setMessage("您打算放弃本次记录嘛？");
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });

        builder.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
