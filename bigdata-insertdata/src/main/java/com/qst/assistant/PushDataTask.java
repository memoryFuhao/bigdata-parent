package com.qst.assistant;

import org.apache.log4j.Logger;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by memory_fu on 2018/12/6.
 */
public class PushDataTask implements Callable<String>{

    protected static final Logger log = Logger.getLogger(PushDataTask.class);

    private AtomicInteger succsessCount;
    private AtomicInteger failCount;
    private String kafkaIp;
    private String jsonData;

    public PushDataTask() {
    }

    public PushDataTask(AtomicInteger succsessCount, AtomicInteger failCount, String kafkaIp,String jsonData) {
        this.succsessCount = succsessCount;
        this.failCount = failCount;
        this.kafkaIp = kafkaIp;
        this.jsonData = jsonData;
    }

    @Override
    public String call() {
        String result = "succsess";
        try {
            // 推送kafka
            succsessCount.incrementAndGet();
        }catch (Exception e){
            result = "fail";
            failCount.incrementAndGet();
            log.info(e.getMessage());
        }
        return result;
    }
}
