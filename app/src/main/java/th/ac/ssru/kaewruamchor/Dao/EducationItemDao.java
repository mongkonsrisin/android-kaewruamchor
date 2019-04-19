package th.ac.ssru.kaewruamchor.Dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by SSRU on 10/1/2561.
 */

public class EducationItemDao {
    @SerializedName("stu_id")
    @Expose
    private String stuId;
    @SerializedName("fa_id")
    @Expose
    private String faId;
    @SerializedName("fa_thainame")
    @Expose
    private String faThainame;
    @SerializedName("fa_engname")
    @Expose
    private String faEngname;
    @SerializedName("ma_faculty")
    @Expose
    private String maFaculty;
    @SerializedName("ma_id")
    @Expose
    private String maId;
    @SerializedName("ma_thainame")
    @Expose
    private String maThainame;
    @SerializedName("ma_engname")
    @Expose
    private String maEngname;
    @SerializedName("level_id")
    @Expose
    private String levelId;
    @SerializedName("level_name")
    @Expose
    private String levelName;
    @SerializedName("fa_logo")
    @Expose
    private String faLogo;

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getFaId() {
        return faId;
    }

    public void setFaId(String faId) {
        this.faId = faId;
    }

    public String getFaThainame() {
        return faThainame;
    }

    public void setFaThainame(String faThainame) {
        this.faThainame = faThainame;
    }

    public String getFaEngname() {
        return faEngname;
    }

    public void setFaEngname(String faEngname) {
        this.faEngname = faEngname;
    }

    public String getMaFaculty() {
        return maFaculty;
    }

    public void setMaFaculty(String maFaculty) {
        this.maFaculty = maFaculty;
    }

    public String getMaId() {
        return maId;
    }

    public void setMaId(String maId) {
        this.maId = maId;
    }

    public String getMaThainame() {
        return maThainame;
    }

    public void setMaThainame(String maThainame) {
        this.maThainame = maThainame;
    }

    public String getMaEngname() {
        return maEngname;
    }

    public void setMaEngname(String maEngname) {
        this.maEngname = maEngname;
    }

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getFaLogo() {
        return faLogo;
    }

    public void setFaLogo(String faLogo) {
        this.faLogo = faLogo;
    }
}
