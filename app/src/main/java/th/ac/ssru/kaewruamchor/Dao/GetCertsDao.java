package th.ac.ssru.kaewruamchor.Dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Kendo on 12/29/2017.
 */

public class GetCertsDao {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("msg")
    @Expose
    private List<Cert> certs = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<Cert> getCerts() {
        return certs;
    }

    public void setCerts(List<Cert> certs) {
        this.certs = certs;
    }

}
