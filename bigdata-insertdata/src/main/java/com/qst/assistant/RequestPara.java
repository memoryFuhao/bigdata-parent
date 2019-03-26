package com.qst.assistant;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by memory_fu on 2018/12/11.
 */
public class RequestPara {

    public int threadCount;     // 并发线程数
    public int time;            // 推送数据时长，单位秒
    public int repuestSecond;   // 每秒发送请求数
    public int totalData;       // 数据推送总条数
    public String kafkaIp;      // kafka机器所在ip
    public String jsonData;     // 推送数据内容
    public AtomicInteger succsessCount = new AtomicInteger(0);     // 推送成功数据条数
    public AtomicInteger failCount = new AtomicInteger(0);         // 推送失败数据条数
    public AtomicInteger sumCount = new AtomicInteger(0);          // 推送数据总条数
    
    public String startDate;	// 开始日期yyyyMMddHH
    public String endDate;		// 结束日期yyyyMMddHH


    public RequestPara() {
    	
    }
    
    public RequestPara(String startDate, String endDate, int threadCount, int time, int repuestSecond) {
    	this(threadCount,time,repuestSecond);
    	this.startDate = startDate;
    	this.endDate = endDate;
    }

    public RequestPara(int threadCount, int time, int repuestSecond) {
        this.threadCount = threadCount;
        this.time = time;
        this.repuestSecond = repuestSecond;
    }

    public RequestPara(int threadCount, int time, int repuestSecond, String kafkaIp, String jsonData) {
    	this(threadCount,time,repuestSecond);
        this.kafkaIp = kafkaIp;
        this.jsonData = jsonData;
    }

    public RequestPara(int threadCount, int repuestSecond, String kafkaIp, int totalData, String jsonData) {
        this.threadCount = threadCount;
        this.repuestSecond = repuestSecond;
        this.totalData = totalData;
        this.kafkaIp = kafkaIp;
        this.jsonData = jsonData;
    }

    public int getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(int threadCount) {
        this.threadCount = threadCount;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getRepuestSecond() {
        return repuestSecond;
    }

    public void setRepuestSecond(int repuestSecond) {
        this.repuestSecond = repuestSecond;
    }

    public int getTotalData() {
        return totalData;
    }

    public void setTotalData(int totalData) {
        this.totalData = totalData;
    }

    public String getKafkaIp() {
        return kafkaIp;
    }

    public void setKafkaIp(String kafkaIp) {
        this.kafkaIp = kafkaIp;
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }

    public AtomicInteger getSuccsessCount() {
        return succsessCount;
    }

    public void setSuccsessCount(AtomicInteger succsessCount) {
        this.succsessCount = succsessCount;
    }

    public AtomicInteger getFailCount() {
        return failCount;
    }

    public void setFailCount(AtomicInteger failCount) {
        this.failCount = failCount;
    }

    public AtomicInteger getSumCount() {
        return sumCount;
    }

    public void setSumCount(AtomicInteger sumCount) {
        this.sumCount = sumCount;
    }

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "RequestPara [threadCount=" + threadCount + ", time=" + time + ", repuestSecond=" + repuestSecond
				+ ", totalData=" + totalData + ", kafkaIp=" + kafkaIp + ", succsessCount=" + succsessCount
				+ ", failCount=" + failCount + ", sumCount=" + sumCount + ", startDate=" + startDate + ", endDate="
				+ endDate + "]";
	}

}
