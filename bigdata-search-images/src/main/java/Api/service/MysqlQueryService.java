package Api.service;

import Api.utils.JdbcOperateUtils;

import java.util.List;

/**
 * Created by liuxiang on 2018/12/18.
 */
public class MysqlQueryService {
    public static void query(String querySentence, String[] object) throws Exception{
        List<String> result = JdbcOperateUtils.singleQuery(querySentence, object);
        for (int i = 0; i < result.size(); i++) {
            //调用查询hbase的接口
//            String key = result.get(i);
            System.out.println(result.get(i));
        }
    }
}