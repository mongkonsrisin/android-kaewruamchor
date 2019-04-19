package th.ac.ssru.kaewruamchor.Dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Hermione on 12/16/2017.
 */

public class Amphur {

    @SerializedName("am_id")
    @Expose
    private String amId;
    @SerializedName("am_thainame")
    @Expose
    private String amThainame;
    @SerializedName("am_engname")
    @Expose
    private String amEngname;
    @SerializedName("am_proid")
    @Expose
    private String amProid;

    public String getAmId() {
        return amId;
    }

    public void setAmId(String amId) {
        this.amId = amId;
    }

    public String getAmThainame() {
        return amThainame;
    }

    public void setAmThainame(String amThainame) {
        this.amThainame = amThainame;
    }

    public String getAmEngname() {
        return amEngname;
    }

    public void setAmEngname(String amEngname) {
        this.amEngname = amEngname;
    }

    public String getAmProid() {
        return amProid;
    }
}
