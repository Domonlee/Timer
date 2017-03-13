package domon.cn.timer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.robertlevonyan.views.chip.Chip;
import com.robertlevonyan.views.chip.OnChipClickListener;
import com.robertlevonyan.views.chip.OnSelectClickListener;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Domon on 2017/3/12.
 * todo need icon
 */

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.BaseViewHolder> {
    private Context context;
    private String[] categroyNames = new String[]{"读书", "休息", "零食", "运动", "吃饭", "睡觉", "学习", "散步", "呼吸", "工作"};


    public CategoriesAdapter(Context context) {
        this.context = context;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater i = LayoutInflater.from(context);
        View view = i.inflate(R.layout.item_categories, parent, false);
        BaseViewHolder viewHolder = new BaseViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.chip.setChipText(categroyNames[position]);
    }

    @Override
    public int getItemCount() {
        return 7;
    }

    class BaseViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_chip)
        Chip chip;

        public BaseViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            chip.setOnChipClickListener(new OnChipClickListener() {
                @Override
                public void onChipClick(View v) {
                    Toast.makeText(context, "Good", Toast.LENGTH_SHORT).show();
                }
            });

            chip.setOnSelectClickListener(new OnSelectClickListener() {
                @Override
                public void onSelectClick(View v, boolean selected) {
                    Logger.i("select");

                }
            });
        }
    }
}
