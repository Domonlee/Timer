package domon.cn.timer.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import domon.cn.timer.App;
import domon.cn.timer.R;
import domon.cn.timer.data.RecordData;

/**
 * Created by Domon on 2017/3/12.
 */

public class StatisticalAdapter extends RecyclerView.Adapter<StatisticalAdapter.BaseViewHolder> {
    private Context context;
    private List<RecordData> recordDatas = new ArrayList<>();

    public StatisticalAdapter(Context context) {
        this.context = context;
        this.recordDatas = App.liteOrm.query(RecordData.class);
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
            holder.hearderLl.setVisibility(View.VISIBLE);
            holder.infoLl.setVisibility(View.GONE);
        } else {
            holder.hearderLl.setVisibility(View.GONE);
            holder.infoLl.setVisibility(View.VISIBLE);
            holder.infoTv.setText(recordDatas.get(position - 1).getCategroy_name() + "--"
                    + recordDatas.get(position - 1).getRecord_time());
        }
    }

    @Override
    public int getItemCount() {
        return (int) App.liteOrm.queryCount(RecordData.class);
    }

    class BaseViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.statistical_header)
        View hearderLl;

        @Bind(R.id.statistical_info_ll)
        LinearLayout infoLl;

        @Bind(R.id.info_title_tv)
        TextView infoTv;

        public BaseViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
