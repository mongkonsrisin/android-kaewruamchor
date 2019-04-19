package th.ac.ssru.kaewruamchor.Dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Hermione on 12/16/2017.
 */

public class DistrictsDao {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("msg")
    @Expose
    private List<District> msg = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<District> getMsg() {
        return msg;
    }

    public void setMsg(List<District> msg) {
        this.msg = msg;
    }
}
