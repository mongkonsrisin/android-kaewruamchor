package th.ac.ssru.kaewruamchor;

/**
 * Created by SSRU on 10/1/2561.
 */

public class Education {
    private String mLogo;
    private String mFaculty;
    private String mMajor;
    private String mDegree;


    public Education(String logo, String faculty, String major, String degree) {
        mLogo = logo;
        mFaculty = faculty;
        mMajor = major;
        mDegree = degree;
    }

    public String getLogo() {
        return mLogo;
    }

    public void setLogo(String mLogo) {
        this.mLogo = mLogo;
    }

    public String getFaculty() {
        return mFaculty;
    }

    public void setmFaculty(String mFaculty) {
        this.mFaculty = mFaculty;
    }

    public String getMajor() {
        return mMajor;
    }

    public void setMajor(String mMajor) {
        this.mMajor = mMajor;
    }

    public String getDegree() {
        return mDegree;
    }

    public void setDegree(String mDegree) {
        this.mDegree = mDegree;
    }
}
