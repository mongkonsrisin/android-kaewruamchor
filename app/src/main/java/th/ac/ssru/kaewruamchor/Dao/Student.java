package th.ac.ssru.kaewruamchor.Dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Hermione on 12/8/2017.
 */

public class Student {
    @SerializedName("degree_name")
    @Expose
    private String degreeName;
    @SerializedName("stu_id")
    @Expose
    private String stuId;
    @SerializedName("stu_prefixid")
    @Expose
    private String stuPrefixid;
    @SerializedName("stu_prefix")
    @Expose
    private String stuPrefix;
    @SerializedName("stu_fname")
    @Expose
    private String stuFname;
    @SerializedName("stu_lname")
    @Expose
    private String stuLname;
    @SerializedName("stu_birthday")
    @Expose
    private String stuBirthday;
    @SerializedName("stu_facultyid")
    @Expose
    private String stuFacultyid;
    @SerializedName("stu_majorid")
    @Expose
    private String stuMajorid;
    @SerializedName("stu_levelid")
    @Expose
    private String stuLevelid;
    @SerializedName("stu_catid")
    @Expose
    private String stuCatid;
    @SerializedName("stu_gen")
    @Expose
    private String stuGen;
    @SerializedName("stu_sec")
    @Expose
    private String stuSec;
    @SerializedName("stu_year")
    @Expose
    private String stuYear;
    @SerializedName("stu_engfname")
    @Expose
    private String stuEngfname;
    @SerializedName("stu_englname")
    @Expose
    private String stuEnglname;
    @SerializedName("stu_job")
    @Expose
    private String stuJob;
    @SerializedName("stu_status")
    @Expose
    private String stuStatus;
    @SerializedName("stu_housenumber")
    @Expose
    private String stuHousenumber;
    @SerializedName("stu_moo")
    @Expose
    private String stuMoo;
    @SerializedName("stu_alley")
    @Expose
    private String stuAlley;
    @SerializedName("stu_street")
    @Expose
    private String stuStreet;
    @SerializedName("stu_district")
    @Expose
    private String stuDistrict;
    @SerializedName("stu_amphur")
    @Expose
    private String stuAmphur;
    @SerializedName("stu_province")
    @Expose
    private String stuProvince;
    @SerializedName("stu_zipcode")
    @Expose
    private String stuZipcode;
    @SerializedName("stu_phonenumber")
    @Expose
    private String stuPhonenumber;
    @SerializedName("stu_email")
    @Expose
    private String stuEmail;
    @SerializedName("stu_facebook")
    @Expose
    private String stuFacebook;
    @SerializedName("stu_line")
    @Expose
    private String stuLine;
    @SerializedName("stu_latitude")
    @Expose
    private String stuLatitude;
    @SerializedName("stu_longtitude")
    @Expose
    private String stuLongtitude;
    @SerializedName("stu_photo")
    @Expose
    private String stuPhoto;
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
    @SerializedName("cat_id")
    @Expose
    private String catId;
    @SerializedName("cat_name")
    @Expose
    private String catName;
    @SerializedName("stu_address")
    @Expose
    private String stuAddress;
    @SerializedName("stu_birthdaytext")
    @Expose
    private String stuBirthdayText;
    @SerializedName("stu_statustext")
    @Expose
    private String stuStatusText;
    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getStuPrefixid() {
        return stuPrefixid;
    }

    public void setStuPrefixid(String stuPrefixid) {
        this.stuPrefixid = stuPrefixid;
    }

    public String getStuPrefix() {
        return stuPrefix;
    }

    public void setStuPrefix(String stuPrefix) {
        this.stuPrefix = stuPrefix;
    }

    public String getStuFname() {
        return stuFname;
    }

    public void setStuFname(String stuFname) {
        this.stuFname = stuFname;
    }

    public String getStuLname() {
        return stuLname;
    }

    public void setStuLname(String stuLname) {
        this.stuLname = stuLname;
    }

    public String getStuBirthday() {
        return stuBirthday;
    }

    public void setStuBirthday(String stuBirthday) {
        this.stuBirthday = stuBirthday;
    }

    public String getStuFacultyid() {
        return stuFacultyid;
    }

    public void setStuFacultyid(String stuFacultyid) {
        this.stuFacultyid = stuFacultyid;
    }

    public String getStuMajorid() {
        return stuMajorid;
    }

    public void setStuMajorid(String stuMajorid) {
        this.stuMajorid = stuMajorid;
    }

    public String getStuLevelid() {
        return stuLevelid;
    }

    public void setStuLevelid(String stuLevelid) {
        this.stuLevelid = stuLevelid;
    }

    public String getStuCatid() {
        return stuCatid;
    }

    public void setStuCatid(String stuCatid) {
        this.stuCatid = stuCatid;
    }

    public String getStuGen() {
        return stuGen;
    }

    public void setStuGen(String stuGen) {
        this.stuGen = stuGen;
    }

    public String getStuSec() {
        return stuSec;
    }

    public void setStuSec(String stuSec) {
        this.stuSec = stuSec;
    }

    public String getStuYear() {
        return stuYear;
    }

    public void setStuYear(String stuYear) {
        this.stuYear = stuYear;
    }

    public String getStuEngfname() {
        return stuEngfname;
    }

    public void setStuEngfname(String stuEngfname) {
        this.stuEngfname = stuEngfname;
    }

    public String getStuEnglname() {
        return stuEnglname;
    }

    public void setStuEnglname(String stuEnglname) {
        this.stuEnglname = stuEnglname;
    }

    public String getStuJob() {
        return stuJob;
    }

    public void setStuJob(String stuJob) {
        this.stuJob = stuJob;
    }

    public String getStuStatus() {
        return stuStatus;
    }

    public void setStuStatus(String stuStatus) {
        this.stuStatus = stuStatus;
    }

    public String getStuHousenumber() {
        return stuHousenumber;
    }

    public void setStuHousenumber(String stuHousenumber) {
        this.stuHousenumber = stuHousenumber;
    }

    public String getStuMoo() {
        return stuMoo;
    }

    public void setStuMoo(String stuMoo) {
        this.stuMoo = stuMoo;
    }

    public String getStuAlley() {
        return stuAlley;
    }

    public void setStuAlley(String stuAlley) {
        this.stuAlley = stuAlley;
    }

    public String getStuStreet() {
        return stuStreet;
    }

    public void setStuStreet(String stuStreet) {
        this.stuStreet = stuStreet;
    }

    public String getStuDistrict() {
        return stuDistrict;
    }

    public void setStuDistrict(String stuDistrict) {
        this.stuDistrict = stuDistrict;
    }

    public String getStuAmphur() {
        return stuAmphur;
    }

    public void setStuAmphur(String stuAmphur) {
        this.stuAmphur = stuAmphur;
    }

    public String getStuProvince() {
        return stuProvince;
    }

    public void setStuProvince(String stuProvince) {
        this.stuProvince = stuProvince;
    }

    public String getStuZipcode() {
        return stuZipcode;
    }

    public void setStuZipcode(String stuZipcode) {
        this.stuZipcode = stuZipcode;
    }

    public String getStuPhonenumber() {
        return stuPhonenumber;
    }

    public void setStuPhonenumber(String stuPhonenumber) {
        this.stuPhonenumber = stuPhonenumber;
    }

    public String getStuEmail() {
        return stuEmail;
    }

    public void setStuEmail(String stuEmail) {
        this.stuEmail = stuEmail;
    }

    public String getStuFacebook() {
        return stuFacebook;
    }

    public void setStuFacebook(String stuFacebook) {
        this.stuFacebook = stuFacebook;
    }

    public String getStuLine() {
        return stuLine;
    }

    public void setStuLine(String stuLine) {
        this.stuLine = stuLine;
    }

    public String getStuLatitude() {
        return stuLatitude;
    }

    public void setStuLatitude(String stuLatitude) {
        this.stuLatitude = stuLatitude;
    }

    public String getStuLongtitude() {
        return stuLongtitude;
    }

    public void setStuLongtitude(String stuLongtitude) {
        this.stuLongtitude = stuLongtitude;
    }

    public String getStuPhoto() {
        return stuPhoto;
    }

    public void setStuPhoto(String stuPhoto) {
        this.stuPhoto = stuPhoto;
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

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getStuAddress() {
        return stuAddress;
    }

    public void setStuAddress(String stuAddress) {
        this.stuAddress = stuAddress;
    }

    public String getDegreeName() {
        return degreeName;
    }

    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }

    public String getStuBirthdayText() {
        return stuBirthdayText;
    }

    public void setStuBirthdayText(String stuBirthdayText) {
        this.stuBirthdayText = stuBirthdayText;
    }

    public String getStuStatusText() {
        return stuStatusText;
    }

    public void setStuStatusText(String stuStatusText) {
        this.stuStatusText = stuStatusText;
    }
}
