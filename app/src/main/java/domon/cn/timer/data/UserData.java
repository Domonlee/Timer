package domon.cn.timer.data;

import com.litesuits.orm.db.annotation.NotNull;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by Domon on 2017/3/14.
 */

@Table("user_table")
public class UserData {
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int id;

    @NotNull
    private String imei;

    public UserData(String imei) {
        this.imei = imei;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }
}
