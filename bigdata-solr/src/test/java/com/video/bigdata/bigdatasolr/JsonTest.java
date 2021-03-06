package com.video.bigdata.bigdatasolr;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.video.bigdata.bigdatasolr.constant.CollectionEnum;
import com.video.bigdata.bigdatasolr.utils.JsonUtils;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class JsonTest {
    @Test
    public void test2(){
        String str = "{\"uuid\":\"a04eab100ca04005b884290330619ead\",\"objType\":1,\"objId\":671,\"serialNumber\":\"1155403522\",\"bigImgURL\":\"http://127.0.0.1:8082/20181120/13/bg/human_1155403522-000671_st633640_end636520.jpg\",\"imgURL\":\"http://127.0.0.1:8082/20181120/13/thumb/human_1155403522-000671_st633640_end636520_thumb.jpg\",\"startFrameIdx\":15840,\"endFrameIdx\":15912,\"startFramePts\":633640,\"endFramePts\":636520,\"createTime\":\"2018-11-20 13:50:43\",\"firstObj\":0,\"snapshot\":{\"frameIdx\":15876,\"width\":1920,\"height\":1080,\"boundingBox\":{\"x\":632,\"y\":68,\"w\":52,\"h\":134}},\"upperClothing\":262144,\"lowerClothing\":1048576,\"features\":{\"faceBoundingBox\":{\"x\":0,\"y\":0,\"h\":0,\"w\":0},\"faceUrl\":\"\",\"coatStyle\":{\"code\":1,\"value\":\"长袖\"},\"trousersStyle\":{\"code\":1,\"value\":\"长裤\"},\"respirator\":-1,\"coatTexture\":1,\"cap\":0,\"hairStyle\":1,\"trousersTexture\":1,\"luggage\":-1,\"trolley\":-1,\"coatColor1\":{\"code\":\"343174\",\"value\":\"棕(卡其)\"},\"coatColor2\":{\"code\":\"-1\",\"value\":\"\"},\"trousersColor1\":{\"code\":\"0\",\"value\":\"黑(深灰)\"},\"trousersColor2\":{\"code\":\"-1\",\"value\":\"\"},\"sex\":2,\"age\":8,\"angle\":512,\"bag\":0,\"carryBag\":0,\"glasses\":0,\"umbrella\":1,\"featureType\":1,\"featureData\":\"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\"}}\n";
        JSONObject jsonObject = JSON.parseObject(str);
        Map<String,Object> map = new ConcurrentHashMap<String,Object>();
        JsonUtils.objectAnalysis(null,jsonObject,map);
        Set<Map.Entry<String, Object>> entries = map.entrySet();
        for(Map.Entry<String, Object> entry : entries){
            //System.out.println(entry.getKey() + "=" + entry.getValue());
            System.out.println("put 'video', 'a04eab100ca04005b884290330619ead', 'human:"+entry.getKey()+"', '"+entry.getValue()+"'");
        }
    }
    @Test
    public void test3(){
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date t = new Date();
        System.out.println(df1.format(t));
        df1.setTimeZone(TimeZone.getTimeZone("UTC"));
        System.out.println(df1.format(t));
    }
    @Test
    public void test4(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        List<Map> list = new ArrayList<>();
        for (int i = 0 ;i< 5;i++){
            Map<String,Object> map = new HashMap<>();
            map.put("key"+i,sdf.format(new Date()));
            map.put("key"+i+i,sdf.format(new Date()));
            list.add(map);
        }
        String s = JSON.toJSONString(list);
        System.out.println(s);
    }
    @Test
    public void test5(){
        final String collectionName = CollectionEnum.getHBaseColumnFamilyNameByCollectionName("humanCollection1");
        if(collectionName == null){
            System.out.println("collectionName is null");
        }
    }
    @Test
    public void test6(){
        int i = 3*17;
        int j = (3<<4)+(3<<0);
        System.out.println(i);
        System.out.println(j);
    }

}
