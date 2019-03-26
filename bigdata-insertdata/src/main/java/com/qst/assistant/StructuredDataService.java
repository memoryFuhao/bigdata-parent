package com.qst.assistant;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.memory_fu.common.util.RandomUtil;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.memory_fu.common.util.DateUtil;

/**
 * Created by memory_fu on 2018/12/11.
 */
public class StructuredDataService {

    protected static final Logger log = Logger.getLogger(StructuredDataService.class);

//    public Timer timer = new Timer();

    /**
     * 峰值推送
     * @param requestPara
     */
    /*
    public void peakPush(final RequestPara requestPara){
        final ExecutorService threadPool = Executors.newFixedThreadPool(requestPara.getThreadCount());

        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                for (int i = 0; i < requestPara.getRepuestSecond(); i++) {
                    try {
                        String value = requestPara.getJsonData();
                        if("null".equalsIgnoreCase(value)){
                            value = generateJsonData();
                        }
                        threadPool.submit(new PushDataTask(requestPara.getSuccsessCount(), requestPara.getFailCount(),
                                requestPara.getKafkaIp(),value));
                        requestPara.getSumCount().incrementAndGet();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, 0, 1000);

        long startTime = System.currentTimeMillis();
        while (true){
            if((System.currentTimeMillis()-startTime) > requestPara.getTime()*1000 ){
                timer.cancel();
                break;
            }
        }

        log.info("执行总时间："+requestPara.getTime()+"s. "+(System.currentTimeMillis()-startTime)/1000+"s.");
        log.info("推送成功条数："+requestPara.getSuccsessCount());
        log.info("推送失败条数："+requestPara.getFailCount());
        log.info("推送总条数："+requestPara.getSumCount());
        log.info("平均每秒推送数据条数："+(Float.valueOf(requestPara.getSuccsessCount().toString())/(float)requestPara.getTime()));
        log.info("请求参数："+requestPara);
    }
*/
    /**
     * 持续推送
     * @param requestPara
     */
    /*
    public void continuedPush(final RequestPara requestPara){
        final ExecutorService threadPool = Executors.newFixedThreadPool(requestPara.getThreadCount());
        final List<Future<String>> futureList = Lists.newArrayList();

        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                for (int i = 0; i < requestPara.getRepuestSecond(); i++) {
                    try {
                        String value = requestPara.getJsonData();
                        if("null".equalsIgnoreCase(value)){
                            value = generateJsonData();
                        }
                        Future<String> submit = threadPool.submit(new PushDataTask(requestPara.getSuccsessCount(), requestPara.getFailCount(),
                                requestPara.getKafkaIp(), value));
                        futureList.add(submit);
                        requestPara.getSumCount().incrementAndGet();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, 0, 1000);

        long startTime = System.currentTimeMillis();
        while (true){
            if((System.currentTimeMillis()-startTime)/1000*requestPara.getRepuestSecond() > requestPara.getTotalData()){
                timer.cancel();
                break;
            }
        }

        threadPool.shutdown();
        int count = 0;
        for (Future<String> future:futureList){
            count++;
        }

        log.info("执行总时间："+requestPara.getTime()+"s. "+(System.currentTimeMillis()-startTime)/1000+"s.");
        log.info("推送成功条数："+requestPara.getSuccsessCount());
        log.info("推送失败条数："+requestPara.getFailCount());
        log.info("推送总条数："+requestPara.getSumCount());
        log.info("数据总条数："+requestPara.getTotalData()+" count:"+count);
        log.info("平均每秒推送数据条数："+(Float.valueOf(requestPara.getSuccsessCount().toString())/(float)((System.currentTimeMillis()-startTime)/1000)));
        log.info("请求参数："+requestPara);

    }
*/
    /**
     * 并发请求solr中数据
     * @param requestPara
     */
    /*
    public void querySolrData(final RequestPara requestPara){
        final ExecutorService threadPool = Executors.newFixedThreadPool(requestPara.getThreadCount());
        final List<Future<String>> futureList = Lists.newArrayList();

        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                for (int i = 0; i < requestPara.getRepuestSecond(); i++) {
                    try {
                        Map<String, Object> data = CommonData.datas.get(CommonData.r.nextInt(CommonData.datas.size()));
                        Map<String, String> queryCondition = createQueryData(String.valueOf(data.get("objType")));
                        queryCondition.put("startDate", requestPara.getStartDate());
                        queryCondition.put("endDate", requestPara.getEndDate());
                        Future<String> submit = threadPool.submit(new QueryDataTask(requestPara.getSuccsessCount(),
                                requestPara.getFailCount(), queryCondition));
                        futureList.add(submit);
                        requestPara.getSumCount().incrementAndGet();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, 0, 1000);

        long startTime = System.currentTimeMillis();
        while (true){
            if((System.currentTimeMillis()-startTime) > requestPara.getTime()*1000){
                timer.cancel();
                break;
            }
        }

        log.info("执行总时间："+requestPara.getTime()+"s. "+(System.currentTimeMillis()-startTime)/1000+"s.");
        log.info("查询完成数据条数："+requestPara.getSuccsessCount());
        log.info("查询失败条数："+requestPara.getFailCount());
        log.info("查询总次数："+requestPara.getSumCount());
        log.info("平均每秒查询成功次数："+(Float.valueOf(requestPara.getSuccsessCount().toString())/(float)requestPara.getTime()));
        log.info("请求参数："+requestPara);
    }
*/
    /**
     * 随机生成一条人、车、人骑车数据
     * @return
     */
    public String generateJsonData(){
        //hkl:生成0或1或2的随机数；
        int index = CommonData.r.nextInt(CommonData.datas.size());
        //hkl:根据index确定选择datas中的第几条数据；
        Map<String,Object> dataMap = CommonData.datas.get(index);

        // 随机组合数据查询条件
        String objType = String.valueOf(dataMap.get("objType"));
        switch (objType){
            case "1": // 人
                Map<String, String> personData = createData(objType);
                //hkl:有些字段是固定的 而有些字段是随机生成的；
                copyMap(personData,dataMap);
                break;
            case "2": // 车
                Map<String, String> vlprData = createData(objType);
                copyMap(vlprData,dataMap);
                break;
            case "4": // 人骑车
                Map<String, String> bikeData = createData(objType);
                copyMap(bikeData,dataMap);
                break;
            default:
                break;
        }

        // 以图搜图用
        dataMap.put("index",CommonData.r.nextInt(100)+1);

        return JSONObject.toJSONString(dataMap);
    }

    /**
     * 复制map内容
     * @param sourceMap
     * @param targetMap
     */
    public void copyMap(Map<String,String> sourceMap,Map<String,Object> targetMap){
        if(sourceMap != null && targetMap != null){
            Set<String> keys = sourceMap.keySet();
            for (String key : keys) {
                targetMap.put(key,sourceMap.get(key));
            }
        }
    }

    /**
     * 随机生成结构化数据（人、车、人骑车）
     * @return
     */
    public Map<String,String> createQueryData(String objType){
        Map<String, String> map = Maps.newHashMap();

        String collectionName = CommonData.collectionRelation.get(objType);
        map.put("collectionName",collectionName);
        map.put("pageNum",RandomUtil.getStr(30)+1);
        map.put("pageSize","20");
        map.put("orderField","createTime");
        map.put("order","ASC");

        map.put("objType",objType);


        switch (objType){
            case "1":
                map.put("features_coatStyle",RandomUtil.getStr(5));
                map.put("features_trousersStyle",RandomUtil.getStr(5));
                map.put("features_respirator",RandomUtil.getStr(5));
                map.put("features_coatTexture",RandomUtil.getStr(5));
                map.put("features_cap",RandomUtil.getStr(5));
                map.put("features_hairStyle",RandomUtil.getStr(5));
                map.put("features_sex",RandomUtil.getStr(2));
                map.put("features_age",RandomUtil.getStr(50));
                map.put("features_bag",RandomUtil.getStr(5));
                map.put("features_glasses",RandomUtil.getStr(3));
                map.put("features_umbrella",RandomUtil.getStr(2));
                break;
            case "2":
                map.put("features_typeCode", RandomUtil.getStr(5));
                String[] brandNames = {"丰田","本田","日产","雷克萨斯","马自达"};
                map.put("features_brandName",brandNames[RandomUtil.getInt(5)]);
                map.put("features_hasCall",RandomUtil.getStr(3));
                map.put("features_hasCrash",RandomUtil.getStr(5));
                map.put("features_hasDanger",RandomUtil.getStr(5));
                map.put("features_colorCode",RandomUtil.getStr(5));
                break;
            case "4":
                map.put("features_sex",RandomUtil.getStr(2));
                map.put("features_age",RandomUtil.getStr(50));
                map.put("features_bag",RandomUtil.getStr(5));
                map.put("features_glasses",RandomUtil.getStr(3));
                map.put("features_umbrella",RandomUtil.getStr(2));
                break;
        }
        return map;
    }


    /**
     * 随机生成结构化数据（人、车、人骑车）
     * @return
     */
    public Map<String,String> createData(String objType){
        Map<String, String> map = Maps.newHashMap();
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        map.put("uuid",uuid);
        map.put("objType",objType);
        map.put("serialNumber", DateUtil.getCurrentDateStr("yyyyMMddHH"));
        map.put("createTime",DateUtil.getCurrentDateStr("yyyy-MM-dd HH:mm:ss"));
        switch (objType){
            case "1":
                map.put("coatStyle",RandomUtil.getStr(5));
                map.put("trousersStyle",RandomUtil.getStr(5));
                map.put("respirator",RandomUtil.getStr(5));
                map.put("coatTexture",RandomUtil.getStr(5));
                map.put("cap",RandomUtil.getStr(5));
                map.put("hairStyle",RandomUtil.getStr(5));
                map.put("sex",RandomUtil.getStr(2));
                map.put("age",RandomUtil.getStr(50));
                map.put("bag",RandomUtil.getStr(5));
                map.put("glasses",RandomUtil.getStr(3));
                map.put("umbrella",RandomUtil.getStr(2));
                break;
            case "2":
                map.put("typeCode",RandomUtil.getStr(5));
                String[] brandNames = {"丰田","本田","日产","雷克萨斯","马自达"};
                map.put("brandName",brandNames[RandomUtil.getInt(5)]);
                map.put("hasCall",RandomUtil.getStr(3));
                map.put("hasCrash",RandomUtil.getStr(5));
                map.put("hasDanger",RandomUtil.getStr(5));
                map.put("colorCode",RandomUtil.getStr(5));
                map.put("hasDanger",RandomUtil.getStr(5));
                break;
            case "4":
                map.put("sex",RandomUtil.getStr(2));
                map.put("age",RandomUtil.getStr(50));
                map.put("bag",RandomUtil.getStr(5));
                map.put("glasses",RandomUtil.getStr(3));
                map.put("umbrella",RandomUtil.getStr(2));
                map.put("coatStyle",RandomUtil.getStr(5));
                map.put("trousersStyle",RandomUtil.getStr(5));
                break;
        }
        return map;
    }


}
