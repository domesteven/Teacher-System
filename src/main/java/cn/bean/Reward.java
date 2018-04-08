package cn.bean;

import java.util.Date;

public class Reward {
    private Integer rId;

    private String rName;

    private String ranking;

    private String formAchievements;

    private String winCategories;

    private String awardUnit;

    private Date awardTime;

    private String remark;

    private Date modifyTime;

    public Integer getrId() {
        return rId;
    }

    public void setrId(Integer rId) {
        this.rId = rId;
    }

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName == null ? null : rName.trim();
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking == null ? null : ranking.trim();
    }

    public String getFormAchievements() {
        return formAchievements;
    }

    public void setFormAchievements(String formAchievements) {
        this.formAchievements = formAchievements == null ? null : formAchievements.trim();
    }

    public String getWinCategories() {
        return winCategories;
    }

    public void setWinCategories(String winCategories) {
        this.winCategories = winCategories == null ? null : winCategories.trim();
    }

    public String getAwardUnit() {
        return awardUnit;
    }

    public void setAwardUnit(String awardUnit) {
        this.awardUnit = awardUnit == null ? null : awardUnit.trim();
    }

    public Date getAwardTime() {
        return awardTime;
    }

    public void setAwardTime(Date awardTime) {
        this.awardTime = awardTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}