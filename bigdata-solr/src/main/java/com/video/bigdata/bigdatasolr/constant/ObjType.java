package com.video.bigdata.bigdatasolr.constant;

/**
 * 使用枚举用于switch判断，增强可读性，并且如果业务逻辑相同，可以减少代码。
 * Created By liwen
 * Date: 2019/3/12 11:13
 */
public enum ObjType {
    HUMAN(1,"humanCollection"),VEHICLE(2,"vehicleCollection"),RIDE(4,"rideCollection");
    private Integer type;
    private String typeName;

    private ObjType(Integer code, String text){
        this.type = code;
        this.typeName = text;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public static String getTypeName(Integer type){
        for (ObjType objType:ObjType.values()){
            if(objType.type == type){
                return objType.typeName;
            }
        }
        return null;
    }

    public static ObjType getByType(Integer type){
        if(type == null){
            return null;
        }
        for (ObjType objType:ObjType.values()){
            if(objType.type == type){
                return objType;
            }
        }
        return null;
    }
}
