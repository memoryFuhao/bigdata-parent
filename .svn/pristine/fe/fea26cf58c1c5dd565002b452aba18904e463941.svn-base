package com.qst.assistant;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import org.apache.log4j.Logger;

import com.google.common.collect.Maps;

import scala.collection.immutable.HashMap;

/**
 * Created by memory_fu on 2018/12/6.
 */
public class QueryDataTask implements Callable<String>{

    protected static final Logger log = Logger.getLogger(QueryDataTask.class);

    private AtomicInteger succsessCount;
    private AtomicInteger failCount;
    private Map<String,String> queryCondition;

    public QueryDataTask() {
    }

    public QueryDataTask(AtomicInteger succsessCount, AtomicInteger failCount, Map<String,String> queryCondition) {
        this.succsessCount = succsessCount;
        this.failCount = failCount;
        this.queryCondition = queryCondition;
    }

    @Override
    public String call() {
        String result = "succsess";
        try {
            String jsonString = mapToQueryStr(queryCondition);
            System.out.println(jsonString);
            // 查询solr中数据

            succsessCount.incrementAndGet();
        }catch (Exception e){
            result = "fail";
            failCount.incrementAndGet();
            log.info(e.getMessage());
        }

        return result;
    }
    
    public String mapToQueryStr(Map<String,String> queryCondition){

        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setCollectionName(queryCondition.get("collectionName"));
        solrQuery.setPageNum(Integer.valueOf(queryCondition.get("pageNum")));
        solrQuery.setPageSize(Integer.valueOf(queryCondition.get("pageSize")));
        solrQuery.setOrder(queryCondition.get("order"));
        solrQuery.setOrderField(queryCondition.get("orderField"));

        List<Query> list = getQueryList(queryCondition);

        solrQuery.setParams(list);

        String jsonString = JSONObject.toJSONString(solrQuery);

        return jsonString;
    }

    public List<Query> getQueryList(Map<String,String> queryCondition){
        List<Query> list = Lists.newArrayList();
        if(queryCondition == null){
            return list;
        }

        Set<String> keySet = queryCondition.keySet();
        for(String key:keySet){
            if(key.startsWith("features")){
                List<String> value = Lists.newArrayList();
                value.add(queryCondition.get(key));

                Query query = new Query();
                query.setField(key);
                query.setType("EQ");
                query.setValue(value);
                list.add(query);
            }
        }

        return list;
    }
    
}
