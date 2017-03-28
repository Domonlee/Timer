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
import domon.cn.timer.utils.CommonUtils;

/**
 * Created by Domon on 2017/3/12.
 * todo can't show the newest record
 */

public class StatisticalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int ITEM_TYPE_HEADER = 0;
    public static final int ITEM_TYPE_INFO = 1;

    private Context context;
    private List<RecordData> recordDatas = new ArrayList<>();

    public StatisticalAdapter(Context context) {
        this.context = context;
        this.recordDatas = App.liteOrm.query(RecordData.class);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_HEADER) {
            return new HeaderViewHolder(LayoutInflater.from(context)
                    .inflate(R.layout.statistical_header_main, parent, false));
        } else {
            return new InfoViewHolder(LayoutInflater.from(context)
                    .inflate(R.layout.item_total_record, parent, false));
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? ITEM_TYPE_HEADER : ITEM_TYPE_INFO;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder) {
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
            int s = 0;
            for (int i = 0; i < recordDatas.size(); i++) {
                s += recordDatas.get(i).getRecord_time();
            }
            headerViewHolder.totalTimeTv.setText(CommonUtils.formatTime(s));
        } else if (holder instanceof InfoViewHolder) {
            InfoViewHolder infoViewHolder = (InfoViewHolder) holder;
            infoViewHolder.infoTv.setText(recordDatas.get(position).getCategroy_name() + "--"
                    + CommonUtils.formatTime(recordDatas.get(position).getRecord_time()));
        }
    }

    @Override
    public int getItemCount() {
        return recordDatas == null ? 0 : (int) App.liteOrm.queryCount(RecordData.class);
    }

    class InfoViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.statistical_info_ll)
        LinearLayout infoLl;

        @Bind(R.id.info_title_tv)
        TextView infoTv;

        public InfoViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.totaltime_tv)
        TextView totalTimeTv;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
