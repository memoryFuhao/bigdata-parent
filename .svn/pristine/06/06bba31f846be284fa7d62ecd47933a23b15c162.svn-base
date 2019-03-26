package com.video.bigdata.bigdatasolr.constant;

/**
 * ${description}
 * Created By liwen
 * Date: 2019/3/19 17:49
 */
public enum CollectionEnum {
    HUNAM("human","humanCollection"),
    VEHICLE("vehicle","vehicleCollection"),
    RIDE("ride","rideCollection");
    //HBase列族名
    private String columnFamilyName;
    //Solr中的Collection名字
    private String collectionName;

    CollectionEnum(String columnFamilyName, String collectionName) {
        this.columnFamilyName = columnFamilyName;
        this.collectionName = collectionName;
    }

    public String getColumnFamilyName() {
        return columnFamilyName;
    }

    public void setColumnFamilyName(String columnFamilyName) {
        this.columnFamilyName = columnFamilyName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public static String getHBaseColumnFamilyNameByCollectionName(String collectionName){
        for (CollectionEnum collectionEnum : CollectionEnum.values()){
            if(collectionEnum.getCollectionName().equals(collectionName)){
                return collectionEnum.getColumnFamilyName();
            }
        }
        return null;
    }

    public static boolean checkCollecionIsExist(String collectionName){
        for (CollectionEnum collectionEnum : CollectionEnum.values()){
            if(collectionEnum.getCollectionName().equals(collectionName)){
                return true;
            }
        }
        return false;
    }
}
