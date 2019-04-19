package th.ac.ssru.kaewruamchor.Dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Hermione on 12/8/2017.
 */

public class GetStudentDao {
    @SerializedName("success")
    @Expose
    private Boolean success;
  /*  @SerializedName("msg")
    @Expose
    private String msg;*/
    @SerializedName("msg")
    @Expose
    private Student student;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

   /* public String getMsg() {
        return msg;
    }*/

   /* public void setMsg(String msg) {
        this.msg = msg;
    }*/

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
    /*@SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("student")
    @Expose
    private Student student;

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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }*/
}
