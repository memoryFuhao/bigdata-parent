package com.video.bigdata.bigdatasolr.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by liuxiang on 2018/12/18.
 */
public class MysqlQueryUtils {
    private static final Logger log = LoggerFactory.getLogger(MysqlQueryUtils.class);
    public static String query(String querySentence, String[] object) throws Exception{
        List<String> result = JdbcOperateUtils.singleQuery(querySentence, object);
//        return result.get(0);
        //调用查询hbase的接口
        return HbaseUtils.getJsonResults("video", result);
    }
}