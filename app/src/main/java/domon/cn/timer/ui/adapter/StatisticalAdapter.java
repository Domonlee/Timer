package domon.cn.timer.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import domon.cn.timer.App;
import domon.cn.timer.R;
import domon.cn.timer.data.CategoriesData;

/**
 * Created by Domon on 2017/3/12.
 */

public class StatisticalAdapter extends RecyclerView.Adapter<StatisticalAdapter.BaseViewHolder> {
    private Context context;
    private List<CategoriesData> categroyNames = new ArrayList<>();

    public StatisticalAdapter(Context context) {
        this.context = context;
        categroyNames = App.liteOrm.query(CategoriesData.class);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater i = LayoutInflater.from(context);
        View view = i.inflate(R.layout.item_total_record, parent, false);
        BaseViewHolder viewHolder = new BaseViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (position == 0) {
            holder.button.setBackgroundResource(R.mipmap.ic_launcher);
        }
    }

    @Override
    public int getItemCount() {
        return (int) App.liteOrm.queryCount(CategoriesData.class);
    }

    class BaseViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.my_btn)
        Button button;

        public BaseViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
