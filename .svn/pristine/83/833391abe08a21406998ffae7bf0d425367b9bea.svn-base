package com.video.bigdata.bigdatasolr;

import com.video.bigdata.bigdatasolr.service.ISolrService;
import com.video.bigdata.bigdatasolr.utils.HbaseUtils;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.LinkedList;
import java.util.List;

/**
 * Service层单元测试
 * @author liwen
 * @create 2019-03-12
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BigdataSolrApplication.class)
public class ServiceTest {
    @Autowired
    private ISolrService solrService;
    @Test
    public void testCount(){
        String str = "{\"feature\": \"SkxUQgkAAAAEAAAAAQAAAE6g81sAAAAAcgAAAAAAAAD4EQAAAAAAAAAYAAAAAAA\",\"bucketId\": 1,\"objType\": 1,\"bucketNum\": 2,\"bucketList\": [8,1,2,3,4],\"limit\": 10}";
        Long count = solrService.count(str);
        System.out.println(count);
    }
    @Test
    public void testImageSearchData(){
        String str = "{\"feature\": \"SkxUQgkAAAAEAAAAAQAAAE6g81sAAAAAcgAAAAAAAAD4EQAAAAAAAAAYAAAAAAA\",\"bucketId\": 1,\"objType\": 1,\"bucketNum\": 2,\"bucketList\": [8,1,2,3,4],\"limit\": 10}";
        String s = solrService.imageSearchData(str);
        System.out.println(s);
    }
    @Test
    public void testQuery(){
        String param = "{\"collectionName\":\"humanCollection\",\"params\":[{\"field\":\"objId\",\"type\":\"EQ\",\"value\":[\"492\"]}],\"orderField\": \"createTime\",\"order\": \"ASC\",\"pageNum\":1,\"pageSize\":10}\n";
        String query = solrService.query(param);
        System.out.println(query);
    }
    @Test
    public void testHBaseUtils(){
        List<String> rowKeys = new LinkedList<>();
        rowKeys.add("123456");
        //rowKeys.add("99e5e64ef970407eb83d24e2f7d739a8");
        HbaseUtils.deleteByRowkey("video",rowKeys);
    }
    @Test
    public void testQueryByCollections(){
        //String param = "{\"collectionName\": [\"humanCollection\",\"vehicleCollection\"],\"params\": [{\"field\": \"objId\",\"type\": \"BETWEEN_AND\",\"value\": [1,20]}],\"pageNum\": 1,\"pageSize\": 10}";
        String param = "{\"collectionName\": [\"humanCollection\",\"vehicleCollection\"],\"params\": [{\"field\": \"serialNumber\",\"type\": \"EQ\",\"value\": [1155403522]}],\"pageNum\": 1,\"pageSize\": 10}";
        final String s = solrService.queryByCollections(param);
        System.out.println(s);
    }
}
