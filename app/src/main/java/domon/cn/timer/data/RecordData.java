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
    private int user_id;

    @NotNull
    private int categroy_id;

    @NotNull
    private String record_time;

    @NotNull
    private String record_date;

    public RecordData(int user_id, int categroy_id, String record_time, String record_date) {
        this.user_id = user_id;
        this.categroy_id = categroy_id;
        this.record_time = record_time;
        this.record_date = record_date;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCategroy_id() {
        return categroy_id;
    }

    public void setCategroy_id(int categroy_id) {
        this.categroy_id = categroy_id;
    }

    public String getRecord_time() {
        return record_time;
    }

    public void setRecord_time(String record_time) {
        this.record_time = record_time;
    }

    public String getRecord_date() {
        return record_date;
    }

    public void setRecord_date(String record_date) {
        this.record_date = record_date;
    }
}
