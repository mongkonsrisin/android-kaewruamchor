package th.ac.ssru.kaewruamchor.Dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Hermione on 12/9/2017.
 */

public class GetFriendsLocationDao {
    /*
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("students")
    @Expose
    private List<Student> student = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<Student> getStudent() {
        return student;
    }

    public void setStudent(List<Student> msg) {
        this.student = msg;
    }*/
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("msg")
    @Expose
    private List<Student> student = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<Student> getStudent() {
        return student;
    }

    public void setStudent(List<Student> msg) {
        this.student = msg;
    }
}
