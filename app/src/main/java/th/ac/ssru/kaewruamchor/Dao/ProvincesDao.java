package th.ac.ssru.kaewruamchor.Dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Hermione on 12/16/2017.
 */

public class ProvincesDao {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("msg")
    @Expose
    private List<Province> msg = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<Province> getMsg() {
        return msg;
    }

    public void setMsg(List<Province> msg) {
        this.msg = msg;
    }
}
