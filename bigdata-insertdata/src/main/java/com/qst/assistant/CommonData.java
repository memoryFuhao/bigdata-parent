package com.qst.assistant;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by memory_fu on 2018/12/20.
 */
public class CommonData {

    public static final String SOLR_QUERY_URL = "";
    public static final String SOLR_MULTI_QUERYURL = "";
    public static final String SOLR_GOUP_QUERYURL = "";
    public static final String SOLR_UPDATE_URL = "";
    public static final String SOLR_DELETE_URL = "";

    public static final String PIC_QUERY_URL = "";

    public static String[] collectionNames = {"人","车","人骑车"};
    public static Map<String,Object> personData = (Map<String,Object>) JSONObject.parse("{\"uuid\":\"fefeeebec2a147a48d2aaa68de4cb3b6\",\"objType\":1,\"objId\":118,\"serialNumber\":\"1155403522\",\"bigImgURL\":\"http://127.0.0.1:8082/20181120/13/bg/human_1155403522-000118_st114520_end116200.jpg\",\"imgURL\":\"http://127.0.0.1:8082/20181120/13/thumb/human_1155403522-000118_st114520_end116200_thumb.jpg\",\"startFrameIdx\":2862,\"endFrameIdx\":2904,\"startFramePts\":114520,\"endFramePts\":116200,\"createTime\":\"2018-11-20 13:49:21\",\"firstObj\":0,\"snapshot\":{\"frameIdx\":2904,\"width\":1920,\"height\":1080,\"boundingBox\":{\"x\":314,\"y\":238,\"w\":52,\"h\":158}},\"upperClothing\":262144,\"lowerClothing\":1048576,\"features\":{\"faceBoundingBox\":{\"x\":0,\"y\":0,\"h\":0,\"w\":0},\"faceUrl\":\"\",\"coatStyle\":{\"code\":1,\"value\":\"长袖\"},\"trousersStyle\":{\"code\":1,\"value\":\"长裤\"},\"respirator\":0,\"coatTexture\":1,\"cap\":0,\"hairStyle\":2,\"trousersTexture\":1,\"luggage\":-1,\"trolley\":-1,\"coatColor1\":{\"code\":\"0\",\"value\":\"黑(深灰)\"},\"coatColor2\":{\"code\":\"-1\",\"value\":\"\"},\"trousersColor1\":{\"code\":\"0\",\"value\":\"黑(深灰)\"},\"trousersColor2\":{\"code\":\"-1\",\"value\":\"\"},\"sex\":1,\"age\":8,\"angle\":256,\"bag\":0,\"carryBag\":0,\"glasses\":0,\"umbrella\":0,\"featureType\":1,\"featureData\":\"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\"}}");
    public static Map<String,Object> carData = (Map<String,Object>)JSONObject.parse("{\"uuid\":\"71699f41fccf40cea7d99324deb997e6\",\"objType\":2,\"objId\":120,\"serialNumber\":\"1155403522\",\"bigImgURL\":\"http://127.0.0.1:8082/20181120/13/bg/vehicle_1155403522-000120_st110920_end119080.jpg\",\"imgURL\":\"http://127.0.0.1:8082/20181120/13/thumb/vehicle_1155403522-000120_st110920_end119080_thumb.jpg\",\"startFrameIdx\":2772,\"endFrameIdx\":2976,\"startFramePts\":110920,\"endFramePts\":119080,\"createTime\":\"2018-11-20 13:49:21\",\"firstObj\":0,\"snapshot\":{\"frameIdx\":2952,\"width\":1920,\"height\":1080,\"boundingBox\":{\"x\":724,\"y\":432,\"w\":344,\"h\":460}},\"features\":{\"type\":\"轿车\",\"typeCode\":\"1\",\"safetyBelt\":{\"mainDriver\":\"-1\",\"coDriver\":\"-1\"},\"brandName\":\"\",\"hasCall\":\"-1\",\"hasCrash\":\"-1\",\"hasDanger\":\"-1\",\"colorCode\":5,\"colorName\":\"灰\",\"tagNum\":0,\"sun\":\"-1\",\"drop\":\"-1\",\"paper\":\"-1\",\"plateColorName\":\"\",\"plateColorCode\":\"\",\"plateLicence\":\"\",\"hasPlate\":\"0\",\"plateClass\":\"\",\"plateClassCode\":\"\",\"sunRoof\":-1,\"spareTire\":-1,\"rack\":-1,\"plateLocationInfo\":{\"x\":0,\"y\":0,\"w\":0,\"h\":0},\"vehicleConfidence\":0,\"plateConfidence\":0,\"featureType\":1,\"featureData\":\"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\"}}");
    public static Map<String,Object> bikeData = (Map<String,Object>)JSONObject.parse("{\"uuid\":\"a66ed5b77be340d2afb5c74c213fcd98\",\"objType\":4,\"objId\":133,\"serialNumber\":\"1155403522\",\"bigImgURL\":\"http://127.0.0.1:8082/20181120/13/bg/bike_1155403522-000133_st121960_end125800.jpg\",\"imgURL\":\"http://127.0.0.1:8082/20181120/13/thumb/bike_1155403522-000133_st121960_end125800_thumb.jpg\",\"startFrameIdx\":3048,\"endFrameIdx\":3144,\"startFramePts\":121960,\"endFramePts\":125800,\"createTime\":\"2018-11-20 13:49:24\",\"firstObj\":0,\"snapshot\":{\"frameIdx\":3132,\"width\":1920,\"height\":1080,\"boundingBox\":{\"x\":1660,\"y\":548,\"w\":142,\"h\":278}},\"upperClothing\":33554432,\"lowerClothing\":-1,\"features\":{\"faceBoundingBox\":{\"x\":0,\"y\":0,\"h\":0,\"w\":0},\"faceUrl\":\"\",\"coatStyle\":{\"code\":1,\"value\":\"长袖\"},\"trousersStyle\":{\"code\":-1,\"value\":\"未知\"},\"respirator\":0,\"coatTexture\":1,\"mainColor1\":{\"code\":\"-1\",\"value\":\"\"},\"mainColor2\":{\"code\":\"-1\",\"value\":\"\"},\"coatColor1\":{\"code\":\"9983\",\"value\":\"红\"},\"coatColor2\":{\"code\":\"-1\",\"value\":\"\"},\"trousersColor1\":{\"code\":\"-1\",\"value\":\"\"},\"trousersColor2\":{\"code\":\"-1\",\"value\":\"\"},\"sex\":2,\"age\":8,\"angle\":128,\"bag\":0,\"carryBag\":-1,\"glasses\":1,\"umbrella\":-1,\"wheels\":2,\"bikeGenre\":2,\"bikeHasPlate\":1,\"helmet\":{\"have\":\"1\",\"color\":\"9983\",\"value\":\"红\"},\"featureType\":1,\"featureData\":\"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\"}}");



    public static Random r = new Random();
    public static Map<String,String> collectionRelation = Maps.newHashMap();
    public static List<Map<String,Object>> datas = Lists.newArrayList();

    static {
        datas.add(CommonData.personData);
        datas.add(CommonData.carData);
        datas.add(CommonData.bikeData);

        collectionRelation.put("1", CommonData.collectionNames[0]);
        collectionRelation.put("2", CommonData.collectionNames[1]);
        collectionRelation.put("4", CommonData.collectionNames[2]);
    }

    public static void main(String[] args) {
        Map<String, String> collectionRelation = CommonData.collectionRelation;
        System.out.println(collectionRelation.size());
        List<Map<String, Object>> datas = CommonData.datas;
        System.out.println(datas.size());
    }


}