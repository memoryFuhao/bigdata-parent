package com.qst.assistant

import java.util.Properties

import com.qst.utils.{HdfsPropertiesUtils, PropertiesUtils}
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

import scala.io.Source

object MessageProducer {

  val prop: Properties = HdfsPropertiesUtils.load("/user/conf/path.properties")
  var properties = new Properties()
  properties.put("bootstrap.servers", "master:9092")
  properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")

  //将json文件的数据插入hbase；
  def produceFromFile(pathname: String): Unit = {
    var producer = new KafkaProducer[String, String](properties)
    try {
      val file = Source.fromFile(pathname)
      for(line <- file.getLines()){
        producer.send(new ProducerRecord[String, String]("test2", line)).get()
      }
      println("send success--------------")
    } catch {
      case e: Exception => e.printStackTrace()
    }finally {
      producer.close()
    }
  }

  def produceFromRandom(num : Int): Unit = {
    var sds: StructuredDataService = new StructuredDataService()
    var producer = new KafkaProducer[String, String](properties)
    try {

      for (i <- 1 to num){
        var line = sds.generateJsonData()
        producer.send(new ProducerRecord[String, String]("test2", line)).get()
      }

      println("send success--------------")
    }catch {
      case e: Exception => e.printStackTrace()
    }finally {
      producer.close()
    }

  }

  def main(args: Array[String]): Unit = {

    if (args(0).equals("1")){
      produceFromFile(args(1))
    }else {
      produceFromRandom(Integer.parseInt(args(1)))

    }
  }
}
