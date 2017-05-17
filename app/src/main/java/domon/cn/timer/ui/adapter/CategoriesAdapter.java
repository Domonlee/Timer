package domon.cn.timer.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.robertlevonyan.views.chip.Chip;
import com.robertlevonyan.views.chip.OnChipClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import domon.cn.timer.App;
import domon.cn.timer.R;
import domon.cn.timer.data.CategoriesData;
import domon.cn.timer.data.Config;

/**
 * Created by Domon on 2017/3/12.
 * todo need icon
 */

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.BaseViewHolder> {
    private Context context;
    private List<CategoriesData> categroyNames = new ArrayList<>();
    private int[] ints = {
            R.color.categoryRead,
            R.color.categoryRest,
            R.color.categorySnacks,
            R.color.categorySport,
            R.color.categoryEat,
            R.color.categorySleep,
            R.color.categoryStudy,
            R.color.categoryWalk,
            R.color.categoryBreath,
            R.color.categoryWork
    };

    public CategoriesAdapter(Context context) {
        this.context = context;
        categroyNames = App.liteOrm.query(CategoriesData.class);
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
        holder.chip.setChipText(categroyNames.get(position).getName());
        holder.chip.changeBackgroundColor(context.getResources().getColor(ints[position]));
    }

    @Override
    public int getItemCount() {
        return (int) App.liteOrm.queryCount(CategoriesData.class);
    }

    class BaseViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_chip)
        Chip chip;

        public BaseViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            chip.setOnChipClickListener(new OnChipClickListener() {
                @Override
                public void onChipClick(View v) {
//                    RecordSaveActivity.actionStart(context);
                    Config.setCategoryName(chip.getChipText());
                    ((Activity) context).finish();
                }
            });
        }
    }
}
