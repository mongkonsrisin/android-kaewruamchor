package th.ac.ssru.kaewruamchor.Dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Hermione on 12/16/2017.
 */

public class District {
    @SerializedName("dis_id")
    @Expose
    private String disId;
    @SerializedName("dis_thainame")
    @Expose
    private String disThainame;
    @SerializedName("dis_engname")
    @Expose
    private String disEngname;
    @SerializedName("dis_zipcode")
    @Expose
    private String disZipcode;
    @SerializedName("dis_amid")
    @Expose
    private String disAmid;
    @SerializedName("dis_proid")
    @Expose
    private String disProid;

    public String getDisId() {
        return disId;
    }

    public void setDisId(String disId) {
        this.disId = disId;
    }

    public String getDisThainame() {
        return disThainame;
    }

    public void setDisThainame(String disThainame) {
        this.disThainame = disThainame;
    }

    public String getDisEngname() {
        return disEngname;
    }

    public void setDisEngname(String disEngname) {
        this.disEngname = disEngname;
    }

    public String getDisZipcode() {
        return disZipcode;
    }

    public void setDisZipcode(String disZipcode) {
        this.disZipcode = disZipcode;
    }

    public String getDisAmid() {
        return disAmid;
    }

    public void setDisAmid(String disAmid) {
        this.disAmid = disAmid;
    }

    public String getDisProid() {
        return disProid;
    }

    public void setDisProid(String disProid) {
        this.disProid = disProid;
    }
}
