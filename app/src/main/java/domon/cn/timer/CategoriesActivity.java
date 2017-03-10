package domon.cn.timer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.robertlevonyan.views.chip.Chip;
import com.robertlevonyan.views.chip.OnChipClickListener;
import com.robertlevonyan.views.chip.OnSelectClickListener;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * todo 添加分类
 */

public class CategoriesActivity extends AppCompatActivity {
    @Bind(R.id.chip)
    Chip mChip;

    public static void actionStart(Context context) {
        Intent i = new Intent(context, CategoriesActivity.class);
        context.startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        ButterKnife.bind(this);

        mChip.setOnChipClickListener(new OnChipClickListener() {
            @Override
            public void onChipClick(View v) {
                Toast.makeText(CategoriesActivity.this, "Good", Toast.LENGTH_SHORT).show();
            }
        });

        mChip.setOnSelectClickListener(new OnSelectClickListener() {
            @Override
            public void onSelectClick(View v, boolean selected) {
                Logger.i("select");

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
