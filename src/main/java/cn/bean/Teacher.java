package cn.bean;

import java.util.Date;

public class Teacher {
    private Integer tId;

    private String tName;

    private String password;

    private Integer authorlever;

    private Date schoolYear;

    private String education;

    private String title;

    private Integer sex;

    private String nativePlace;

    private String certificateNumber;

    private String image;

    private String major;

    private String graduateSchool;

    private String teachingResearch;

    private String job;

    private Date modifyTime;

    public Integer gettId() {
        return tId;
    }

    public void settId(Integer tId) {
        this.tId = tId;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName == null ? null : tName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getAuthorlever() {
        return authorlever;
    }

    public void setAuthorlever(Integer authorlever) {
        this.authorlever = authorlever;
    }

    public Date getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(Date schoolYear) {
        this.schoolYear = schoolYear;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace == null ? null : nativePlace.trim();
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber == null ? null : certificateNumber.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major == null ? null : major.trim();
    }

    public String getGraduateSchool() {
        return graduateSchool;
    }

    public void setGraduateSchool(String graduateSchool) {
        this.graduateSchool = graduateSchool == null ? null : graduateSchool.trim();
    }

    public String getTeachingResearch() {
        return teachingResearch;
    }

    public void setTeachingResearch(String teachingResearch) {
        this.teachingResearch = teachingResearch == null ? null : teachingResearch.trim();
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job == null ? null : job.trim();
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}