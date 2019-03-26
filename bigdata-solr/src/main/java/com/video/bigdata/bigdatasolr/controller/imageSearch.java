package com.video.bigdata.bigdatasolr.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.video.bigdata.bigdatasolr.service.ISearchService;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.slf4j.SLF4JLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by liuxiang on 2019/3/8.
 */
@RestController
public class imageSearch {
    private static final Logger log = LoggerFactory.getLogger(SLF4JLogger.class);
    @Autowired
    private ISearchService searchService;

    @RequestMapping(value = "/search",method = RequestMethod.POST)
    @ResponseBody
    public String query(@RequestBody String paramsJson){
        JSONObject jsonObject = JSONObject.parseObject(paramsJson);
        final String feature = jsonObject.getString("feature");
        final Integer objType = jsonObject.getInteger("objType");
        final Integer bucketNum = jsonObject.getInteger("bucketNum");
        final JSONArray bucketList = jsonObject.getJSONArray("bucketList");
        final Integer limit = jsonObject.getInteger("limit");
        if(StringUtils.isBlank(feature)){
            return "{'Error':'The parameter of feature is null!'}";
        }
        if(objType == null){
            return "{'Error':'The parameter of objType is null!'}";
        }
        if(bucketNum == null){
            return "{'Error':'The parameter of bucketNum is null!'}";
        }
        if(bucketList == null || bucketList.isEmpty()){
            return "{'Error':'The parameter of bucketList is null!'}";
        }
        if(limit == null){
            return "{'Error':'The parameter of limit is null!'}";
        }
        return searchService.searchImage(paramsJson);
    }
}
