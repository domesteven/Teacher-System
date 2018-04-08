package cn.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RewardExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RewardExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andRIdIsNull() {
            addCriterion("r_id is null");
            return (Criteria) this;
        }

        public Criteria andRIdIsNotNull() {
            addCriterion("r_id is not null");
            return (Criteria) this;
        }

        public Criteria andRIdEqualTo(Integer value) {
            addCriterion("r_id =", value, "rId");
            return (Criteria) this;
        }

        public Criteria andRIdNotEqualTo(Integer value) {
            addCriterion("r_id <>", value, "rId");
            return (Criteria) this;
        }

        public Criteria andRIdGreaterThan(Integer value) {
            addCriterion("r_id >", value, "rId");
            return (Criteria) this;
        }

        public Criteria andRIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("r_id >=", value, "rId");
            return (Criteria) this;
        }

        public Criteria andRIdLessThan(Integer value) {
            addCriterion("r_id <", value, "rId");
            return (Criteria) this;
        }

        public Criteria andRIdLessThanOrEqualTo(Integer value) {
            addCriterion("r_id <=", value, "rId");
            return (Criteria) this;
        }

        public Criteria andRIdIn(List<Integer> values) {
            addCriterion("r_id in", values, "rId");
            return (Criteria) this;
        }

        public Criteria andRIdNotIn(List<Integer> values) {
            addCriterion("r_id not in", values, "rId");
            return (Criteria) this;
        }

        public Criteria andRIdBetween(Integer value1, Integer value2) {
            addCriterion("r_id between", value1, value2, "rId");
            return (Criteria) this;
        }

        public Criteria andRIdNotBetween(Integer value1, Integer value2) {
            addCriterion("r_id not between", value1, value2, "rId");
            return (Criteria) this;
        }

        public Criteria andRNameIsNull() {
            addCriterion("r_name is null");
            return (Criteria) this;
        }

        public Criteria andRNameIsNotNull() {
            addCriterion("r_name is not null");
            return (Criteria) this;
        }

        public Criteria andRNameEqualTo(String value) {
            addCriterion("r_name =", value, "rName");
            return (Criteria) this;
        }

        public Criteria andRNameNotEqualTo(String value) {
            addCriterion("r_name <>", value, "rName");
            return (Criteria) this;
        }

        public Criteria andRNameGreaterThan(String value) {
            addCriterion("r_name >", value, "rName");
            return (Criteria) this;
        }

        public Criteria andRNameGreaterThanOrEqualTo(String value) {
            addCriterion("r_name >=", value, "rName");
            return (Criteria) this;
        }

        public Criteria andRNameLessThan(String value) {
            addCriterion("r_name <", value, "rName");
            return (Criteria) this;
        }

        public Criteria andRNameLessThanOrEqualTo(String value) {
            addCriterion("r_name <=", value, "rName");
            return (Criteria) this;
        }

        public Criteria andRNameLike(String value) {
            addCriterion("r_name like", value, "rName");
            return (Criteria) this;
        }

        public Criteria andRNameNotLike(String value) {
            addCriterion("r_name not like", value, "rName");
            return (Criteria) this;
        }

        public Criteria andRNameIn(List<String> values) {
            addCriterion("r_name in", values, "rName");
            return (Criteria) this;
        }

        public Criteria andRNameNotIn(List<String> values) {
            addCriterion("r_name not in", values, "rName");
            return (Criteria) this;
        }

        public Criteria andRNameBetween(String value1, String value2) {
            addCriterion("r_name between", value1, value2, "rName");
            return (Criteria) this;
        }

        public Criteria andRNameNotBetween(String value1, String value2) {
            addCriterion("r_name not between", value1, value2, "rName");
            return (Criteria) this;
        }

        public Criteria andRankingIsNull() {
            addCriterion("ranking is null");
            return (Criteria) this;
        }

        public Criteria andRankingIsNotNull() {
            addCriterion("ranking is not null");
            return (Criteria) this;
        }

        public Criteria andRankingEqualTo(String value) {
            addCriterion("ranking =", value, "ranking");
            return (Criteria) this;
        }

        public Criteria andRankingNotEqualTo(String value) {
            addCriterion("ranking <>", value, "ranking");
            return (Criteria) this;
        }

        public Criteria andRankingGreaterThan(String value) {
            addCriterion("ranking >", value, "ranking");
            return (Criteria) this;
        }

        public Criteria andRankingGreaterThanOrEqualTo(String value) {
            addCriterion("ranking >=", value, "ranking");
            return (Criteria) this;
        }

        public Criteria andRankingLessThan(String value) {
            addCriterion("ranking <", value, "ranking");
            return (Criteria) this;
        }

        public Criteria andRankingLessThanOrEqualTo(String value) {
            addCriterion("ranking <=", value, "ranking");
            return (Criteria) this;
        }

        public Criteria andRankingLike(String value) {
            addCriterion("ranking like", value, "ranking");
            return (Criteria) this;
        }

        public Criteria andRankingNotLike(String value) {
            addCriterion("ranking not like", value, "ranking");
            return (Criteria) this;
        }

        public Criteria andRankingIn(List<String> values) {
            addCriterion("ranking in", values, "ranking");
            return (Criteria) this;
        }

        public Criteria andRankingNotIn(List<String> values) {
            addCriterion("ranking not in", values, "ranking");
            return (Criteria) this;
        }

        public Criteria andRankingBetween(String value1, String value2) {
            addCriterion("ranking between", value1, value2, "ranking");
            return (Criteria) this;
        }

        public Criteria andRankingNotBetween(String value1, String value2) {
            addCriterion("ranking not between", value1, value2, "ranking");
            return (Criteria) this;
        }

        public Criteria andFormAchievementsIsNull() {
            addCriterion("form_achievements is null");
            return (Criteria) this;
        }

        public Criteria andFormAchievementsIsNotNull() {
            addCriterion("form_achievements is not null");
            return (Criteria) this;
        }

        public Criteria andFormAchievementsEqualTo(String value) {
            addCriterion("form_achievements =", value, "formAchievements");
            return (Criteria) this;
        }

        public Criteria andFormAchievementsNotEqualTo(String value) {
            addCriterion("form_achievements <>", value, "formAchievements");
            return (Criteria) this;
        }

        public Criteria andFormAchievementsGreaterThan(String value) {
            addCriterion("form_achievements >", value, "formAchievements");
            return (Criteria) this;
        }

        public Criteria andFormAchievementsGreaterThanOrEqualTo(String value) {
            addCriterion("form_achievements >=", value, "formAchievements");
            return (Criteria) this;
        }

        public Criteria andFormAchievementsLessThan(String value) {
            addCriterion("form_achievements <", value, "formAchievements");
            return (Criteria) this;
        }

        public Criteria andFormAchievementsLessThanOrEqualTo(String value) {
            addCriterion("form_achievements <=", value, "formAchievements");
            return (Criteria) this;
        }

        public Criteria andFormAchievementsLike(String value) {
            addCriterion("form_achievements like", value, "formAchievements");
            return (Criteria) this;
        }

        public Criteria andFormAchievementsNotLike(String value) {
            addCriterion("form_achievements not like", value, "formAchievements");
            return (Criteria) this;
        }

        public Criteria andFormAchievementsIn(List<String> values) {
            addCriterion("form_achievements in", values, "formAchievements");
            return (Criteria) this;
        }

        public Criteria andFormAchievementsNotIn(List<String> values) {
            addCriterion("form_achievements not in", values, "formAchievements");
            return (Criteria) this;
        }

        public Criteria andFormAchievementsBetween(String value1, String value2) {
            addCriterion("form_achievements between", value1, value2, "formAchievements");
            return (Criteria) this;
        }

        public Criteria andFormAchievementsNotBetween(String value1, String value2) {
            addCriterion("form_achievements not between", value1, value2, "formAchievements");
            return (Criteria) this;
        }

        public Criteria andWinCategoriesIsNull() {
            addCriterion("win_categories is null");
            return (Criteria) this;
        }

        public Criteria andWinCategoriesIsNotNull() {
            addCriterion("win_categories is not null");
            return (Criteria) this;
        }

        public Criteria andWinCategoriesEqualTo(String value) {
            addCriterion("win_categories =", value, "winCategories");
            return (Criteria) this;
        }

        public Criteria andWinCategoriesNotEqualTo(String value) {
            addCriterion("win_categories <>", value, "winCategories");
            return (Criteria) this;
        }

        public Criteria andWinCategoriesGreaterThan(String value) {
            addCriterion("win_categories >", value, "winCategories");
            return (Criteria) this;
        }

        public Criteria andWinCategoriesGreaterThanOrEqualTo(String value) {
            addCriterion("win_categories >=", value, "winCategories");
            return (Criteria) this;
        }

        public Criteria andWinCategoriesLessThan(String value) {
            addCriterion("win_categories <", value, "winCategories");
            return (Criteria) this;
        }

        public Criteria andWinCategoriesLessThanOrEqualTo(String value) {
            addCriterion("win_categories <=", value, "winCategories");
            return (Criteria) this;
        }

        public Criteria andWinCategoriesLike(String value) {
            addCriterion("win_categories like", value, "winCategories");
            return (Criteria) this;
        }

        public Criteria andWinCategoriesNotLike(String value) {
            addCriterion("win_categories not like", value, "winCategories");
            return (Criteria) this;
        }

        public Criteria andWinCategoriesIn(List<String> values) {
            addCriterion("win_categories in", values, "winCategories");
            return (Criteria) this;
        }

        public Criteria andWinCategoriesNotIn(List<String> values) {
            addCriterion("win_categories not in", values, "winCategories");
            return (Criteria) this;
        }

        public Criteria andWinCategoriesBetween(String value1, String value2) {
            addCriterion("win_categories between", value1, value2, "winCategories");
            return (Criteria) this;
        }

        public Criteria andWinCategoriesNotBetween(String value1, String value2) {
            addCriterion("win_categories not between", value1, value2, "winCategories");
            return (Criteria) this;
        }

        public Criteria andAwardUnitIsNull() {
            addCriterion("award_unit is null");
            return (Criteria) this;
        }

        public Criteria andAwardUnitIsNotNull() {
            addCriterion("award_unit is not null");
            return (Criteria) this;
        }

        public Criteria andAwardUnitEqualTo(String value) {
            addCriterion("award_unit =", value, "awardUnit");
            return (Criteria) this;
        }

        public Criteria andAwardUnitNotEqualTo(String value) {
            addCriterion("award_unit <>", value, "awardUnit");
            return (Criteria) this;
        }

        public Criteria andAwardUnitGreaterThan(String value) {
            addCriterion("award_unit >", value, "awardUnit");
            return (Criteria) this;
        }

        public Criteria andAwardUnitGreaterThanOrEqualTo(String value) {
            addCriterion("award_unit >=", value, "awardUnit");
            return (Criteria) this;
        }

        public Criteria andAwardUnitLessThan(String value) {
            addCriterion("award_unit <", value, "awardUnit");
            return (Criteria) this;
        }

        public Criteria andAwardUnitLessThanOrEqualTo(String value) {
            addCriterion("award_unit <=", value, "awardUnit");
            return (Criteria) this;
        }

        public Criteria andAwardUnitLike(String value) {
            addCriterion("award_unit like", value, "awardUnit");
            return (Criteria) this;
        }

        public Criteria andAwardUnitNotLike(String value) {
            addCriterion("award_unit not like", value, "awardUnit");
            return (Criteria) this;
        }

        public Criteria andAwardUnitIn(List<String> values) {
            addCriterion("award_unit in", values, "awardUnit");
            return (Criteria) this;
        }

        public Criteria andAwardUnitNotIn(List<String> values) {
            addCriterion("award_unit not in", values, "awardUnit");
            return (Criteria) this;
        }

        public Criteria andAwardUnitBetween(String value1, String value2) {
            addCriterion("award_unit between", value1, value2, "awardUnit");
            return (Criteria) this;
        }

        public Criteria andAwardUnitNotBetween(String value1, String value2) {
            addCriterion("award_unit not between", value1, value2, "awardUnit");
            return (Criteria) this;
        }

        public Criteria andAwardTimeIsNull() {
            addCriterion("award_time is null");
            return (Criteria) this;
        }

        public Criteria andAwardTimeIsNotNull() {
            addCriterion("award_time is not null");
            return (Criteria) this;
        }

        public Criteria andAwardTimeEqualTo(Date value) {
            addCriterion("award_time =", value, "awardTime");
            return (Criteria) this;
        }

        public Criteria andAwardTimeNotEqualTo(Date value) {
            addCriterion("award_time <>", value, "awardTime");
            return (Criteria) this;
        }

        public Criteria andAwardTimeGreaterThan(Date value) {
            addCriterion("award_time >", value, "awardTime");
            return (Criteria) this;
        }

        public Criteria andAwardTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("award_time >=", value, "awardTime");
            return (Criteria) this;
        }

        public Criteria andAwardTimeLessThan(Date value) {
            addCriterion("award_time <", value, "awardTime");
            return (Criteria) this;
        }

        public Criteria andAwardTimeLessThanOrEqualTo(Date value) {
            addCriterion("award_time <=", value, "awardTime");
            return (Criteria) this;
        }

        public Criteria andAwardTimeIn(List<Date> values) {
            addCriterion("award_time in", values, "awardTime");
            return (Criteria) this;
        }

        public Criteria andAwardTimeNotIn(List<Date> values) {
            addCriterion("award_time not in", values, "awardTime");
            return (Criteria) this;
        }

        public Criteria andAwardTimeBetween(Date value1, Date value2) {
            addCriterion("award_time between", value1, value2, "awardTime");
            return (Criteria) this;
        }

        public Criteria andAwardTimeNotBetween(Date value1, Date value2) {
            addCriterion("award_time not between", value1, value2, "awardTime");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNull() {
            addCriterion("modify_time is null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNotNull() {
            addCriterion("modify_time is not null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeEqualTo(Date value) {
            addCriterion("modify_time =", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotEqualTo(Date value) {
            addCriterion("modify_time <>", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThan(Date value) {
            addCriterion("modify_time >", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("modify_time >=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThan(Date value) {
            addCriterion("modify_time <", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThanOrEqualTo(Date value) {
            addCriterion("modify_time <=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIn(List<Date> values) {
            addCriterion("modify_time in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotIn(List<Date> values) {
            addCriterion("modify_time not in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeBetween(Date value1, Date value2) {
            addCriterion("modify_time between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotBetween(Date value1, Date value2) {
            addCriterion("modify_time not between", value1, value2, "modifyTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}