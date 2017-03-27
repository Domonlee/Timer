package domon.cn.timer.data;

import com.litesuits.orm.db.annotation.NotNull;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by Domon on 2017/3/14.
 */

@Table("record_table")
public class RecordData {
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int id;

    @NotNull
    private String user_imei;

    @NotNull
    private String categroy_name;

    @NotNull
    private int record_time;

    @NotNull
    private String record_date;

    public RecordData(String user_imei, String categroy_name, int record_time, String record_date) {
        this.user_imei = user_imei;
        this.record_time = record_time;
        this.categroy_name = categroy_name;
        this.record_date = record_date;
    }

    public String getUser_imei() {
        return user_imei;
    }

    public void setUser_imei(String user_imei) {
        this.user_imei = user_imei;
    }

    public String getCategroy_name() {
        return categroy_name;
    }

    public void setCategroy_name(String categroy_name) {
        this.categroy_name = categroy_name;
    }

    public int getRecord_time() {
        return record_time;
    }

    public void setRecord_time(int record_time) {
        this.record_time = record_time;
    }

    public String getRecord_date() {
        return record_date;
    }

    public void setRecord_date(String record_date) {
        this.record_date = record_date;
    }
}
