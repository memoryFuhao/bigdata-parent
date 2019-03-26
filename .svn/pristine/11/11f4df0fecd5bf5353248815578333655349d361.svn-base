package com.video.bigdata.bigdatasolr.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class JsonUtils {

    public static void objectAnalysis(String fatherKey, JSONObject obj, Map<String, Object> map){
        Set<String> set = obj.keySet();
        Iterator<String> iter = set.iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            Object jsonValue = obj.get(key);
            if (isObject(jsonValue.toString())) {
                JSONObject objtmp = obj.parseObject(jsonValue.toString());
                objectAnalysis((StringUtils.isBlank(fatherKey) ? key : fatherKey+"_"+key), objtmp, map);
            }else{
                map.put(StringUtils.isBlank(fatherKey) ? key : fatherKey+"_"+key, jsonValue.toString());
            }
        }
    }

    public static boolean isObject(String jsonStr){
        if(StringUtils.isBlank(jsonStr))
            return false;
        try{
            Object obj = JSONObject.parse(jsonStr);
            if (obj instanceof JSONObject){
                return true;
            }
        }catch (Exception e){
            return false;
        }
        return false;
    }

    /**
     * 对于类似于嵌套实体，如：HumanBean - Features features - FaceBoundingBox faceBoundingBox - Integer x=3，
     * 此方法返回的是features_faceBoundingBox_x=3
     * java.lang.StackOverflowError
     * @author liwen
     * @param
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @Deprecated
    public static Map<String,Object> convertJsonToHBaseFormat(Object object,String fatherClassName) throws IllegalAccessException{
        //标记递归的结束条件
        boolean flag = true;
        Map<String,Object> map = new ConcurrentHashMap<>();
        Field[] fields = object.getClass().getDeclaredFields();
        for(Field field:fields){
            field.setAccessible(true);
            //判断是否是Java语言规范定义的一种合成类
            if(field.isSynthetic()){
                map.put(StringUtils.isBlank(fatherClassName) ? field.getName() : fatherClassName+"_"+field.getName(),field.get(object));
            }else{
                Map<String, Object> returnMap = convertJsonToHBaseFormat(field, StringUtils.isBlank(fatherClassName) ? field.getName() : fatherClassName+"_"+field.getName());
                map.putAll(returnMap);
                flag = false;
            }
        }
        if(flag && StringUtils.isNotBlank(fatherClassName)){
            //递归时返回
            return map;
        }
        return map;
    }

}
