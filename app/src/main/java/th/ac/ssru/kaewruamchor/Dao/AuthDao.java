package th.ac.ssru.kaewruamchor.Dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Hermione on 12/8/2017.
 */

public class AuthDao {
    @SerializedName("success")
    @Expose
    private Boolean success;
   /* @SerializedName("msg")
    @Expose
    private String msg;*/
    @SerializedName("msg")
    @Expose
    private String user;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    /*public String getMsg() {
        return msg;
    }*/

   /* public void setMsg(String msg) {
        this.msg = msg;
    }*/

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
/*
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("user")
    @Expose
    private String user;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
*/
}