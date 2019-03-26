package Api.service;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by liuxiang on 2019/1/8.
 */
public class FinalService {
    private static final Logger log = LoggerFactory.getLogger(FinalService.class);
    public static void main(String[] args) {
        //随机生成keyId
        StringBuffer keyId = new StringBuffer(String.valueOf(System.currentTimeMillis()));
        for (int i = 0; i < 4; i++){
            keyId.append((char)(Math.random() * 26 + 97));
        }

        //json格式数据，包括featrue、index、objType、buketNum、indexAdjoin数组和limit
        String jsonData = "{\n" +
                "    \"feature\": \"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\", \n" +
                "\"index\": 1, \n" +
                "\"objType\":4,\n" +
                "\"buketNum\":30,\n" +
                "    \"indexAdjoin\": [\n" +
                "        1, \n" +
                "        2, \n" +
                "        3, \n" +
                "        4\n" +
                "    ], \n" +
                "    \"limit\": 15\n" +
                "}\n";


        //jsonData拼接keyId成为jsonObject
        JSONObject jsonObject = JSONObject.parseObject(jsonData);
        jsonObject.put("keyId", keyId);

        //获取json中的limit属性
        int limit = jsonObject.getInteger("limit");

        //调用向kafka写入数据
        KafkaProducerService.produce(jsonObject.toString());

        //若干秒后查询msql
        try {
            Thread.sleep(6000);
        } catch (Exception e) {
            log.error("线程不安全！", e);
        }

        //查询mysql数据
        String querySentence = "SELECT uuid from results WHERE ack = ? limit " + limit;
        String[] object = new String[]{keyId.toString()};
        try {
            MysqlQueryService.query(querySentence, object);
        } catch (Exception e) {
            log.error("数据库错误！", e);
        }
    }
}
