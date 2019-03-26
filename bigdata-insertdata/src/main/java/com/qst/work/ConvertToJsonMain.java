package com.qst.work;

import com.qst.utils.HbaseUtils;
import com.qst.utils.HdfsPropertiesUtils;
import com.qst.utils.PropertiesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * created by hkl
 */

public class ConvertToJsonMain {

    private static Logger logger = LoggerFactory.getLogger(ConvertToJsonMain.class);

    public static void main(String[] args) {
        try{
            Properties hdfsConfiguration = HdfsPropertiesUtils.load("/user/conf/path.properties");
            String table = hdfsConfiguration.getProperty("table");
            String res = HbaseUtils.convertToJsonStr(table, args[0]);
            System.out.println(res);
        }catch (Exception e){
            logger.info("load property file failed!");
        }

    }
}
