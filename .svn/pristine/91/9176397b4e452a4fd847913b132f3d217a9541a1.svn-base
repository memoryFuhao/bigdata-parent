package com.video.bigdata.bigdatasolr.constant;

/**
 * Created By liwen
 * Date: 2019/3/12 20:41
 */
public enum ConditionType {
    EQ("EQ"),
    NOT_EQ("NOT_EQ"),
    LESS("LESS"),
    LESS_EQ("LESS_EQ"),
    MORE("MORE"),
    MORE_EQ("MORE_EQ"),
    LIKE("LIKE"),
    IN("IN"),
    BETWEEN_AND("BETWEEN_AND"),
    ISNULL("ISNULL"),
    ISNOTNULL("ISNOTNULL");

    private String condition;

    private ConditionType(String condition){
        this.condition = condition;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public static ConditionType getEnum(String condition){
        for (ConditionType type:ConditionType.values()){
            if(type.getCondition().equals(condition)){
                return type;
            }
        }
        return null;
    }
}
