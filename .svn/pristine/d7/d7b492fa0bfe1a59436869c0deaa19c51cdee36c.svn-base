package com.video.bigdata.bigdatasolr.service.impl;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.video.bigdata.bigdatasolr.service.ISearchService;
import com.video.bigdata.bigdatasolr.utils.KafkaProducerUtils;
import com.video.bigdata.bigdatasolr.utils.MysqlQueryUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by liuxiang on 2019/3/8.
 */
@Service
public class SearchService implements ISearchService {
    private static final Logger log = LoggerFactory.getLogger(SearchService.class);

    @Override
    public String searchImage(String paramsJson) {
        //keyId = new StringBuffer("lxtest");
        //jsonData拼接keyId成为jsonObject
        JSONObject jsonObject = JSONObject.parseObject(paramsJson);
        //随机生成keyId
        StringBuffer keyId = new StringBuffer(String.valueOf(System.currentTimeMillis()));
        for (int i = 0; i < 4; i++){
            keyId.append((char)(Math.random() * 26 + 97));
        }
        jsonObject.put("keyId", keyId);

        int limit = 10;
        if (jsonObject.getInteger("limit") != null) {
            limit = jsonObject.getInteger("limit");
        }

        //调用向kafka写入数据
        KafkaProducerUtils.produce(jsonObject.toString());

        //若干秒后查询msql
        try {
            Thread.sleep(6000);
        } catch (Exception e) {
            log.error("线程不安全！", e);
        }

        //查询mysql数据
        String querySentence = "SELECT uuid from results WHERE ack = ? limit " + limit;
        String[] object = new String[]{keyId.toString()};
        String result = "";
        try {
            result = MysqlQueryUtils.query(querySentence, object);
        } catch (Exception e) {
            log.error("数据库查询错误！", e);
        }

        return result;
    }
}
