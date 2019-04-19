package th.ac.ssru.kaewruamchor.Dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by SSRU on 10/1/2561.
 */

public class EducationListDao {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("msg")
    @Expose
    private List<EducationItemDao> educationItemDao = null;


    public List<EducationItemDao> getEducationItemDao() {
        return educationItemDao;
    }

    public void setEducationItemDao(List<EducationItemDao> educationItemDao) {
        this.educationItemDao = educationItemDao;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
