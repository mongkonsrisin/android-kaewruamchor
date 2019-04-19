package th.ac.ssru.kaewruamchor.Dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Hermione on 12/16/2017.
 */

public class Province {
    @SerializedName("pro_id")
    @Expose
    private String proId;
    @SerializedName("pro_thainame")
    @Expose
    private String proThainame;
    @SerializedName("pro_engname")
    @Expose
    private String proEngname;
    @SerializedName("pro_geocode")
    @Expose
    private String proGeocode;

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getProThainame() {
        return proThainame;
    }

    public void setProThainame(String proThainame) {
        this.proThainame = proThainame;
    }

    public String getProEngname() {
        return proEngname;
    }

    public void setProEngname(String proEngname) {
        this.proEngname = proEngname;
    }

    public String getProGeocode() {
        return proGeocode;
    }

    public void setProGeocode(String proGeocode) {
        this.proGeocode = proGeocode;
    }
}
