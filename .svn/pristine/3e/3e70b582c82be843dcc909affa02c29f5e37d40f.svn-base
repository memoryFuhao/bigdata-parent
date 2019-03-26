package com.video.bigdata.bigdatasolr.utils;

/**
 * Created by liuxiang on 2018/12/12.
 */

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class KafkaProducerUtils {
    //json格式数据，包括keyId、featrue、index、objType、buketNum、indexAdjoin数组和limit
    public static void produce(String jsonData) {
        Properties props = new Properties();
        Properties prop = PropertiesReaderUtils.getProperties("conf/path.properties");
        props.put("bootstrap.servers",  prop.getProperty("metadata.broker.list"));
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //生产者发送消息
        String topic = prop.getProperty("topic.picture");
        Producer<String, String> procuder = new KafkaProducer<String,String>(props);
        ProducerRecord<String, String> msg = new ProducerRecord<String, String>(topic, jsonData);
        System.out.println(jsonData);
        procuder.send(msg);
        procuder.close();
    }
}
