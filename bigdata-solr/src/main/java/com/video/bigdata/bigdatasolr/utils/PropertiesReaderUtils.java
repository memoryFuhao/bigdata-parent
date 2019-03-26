package com.video.bigdata.bigdatasolr.utils;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 读取配置文件工具类
 * Created by liuxiang on 2018/12/10.
 */
public class PropertiesReaderUtils {
    static Map<String, Properties> props = new HashMap<String, Properties>();
    public static Properties getProperties(String confPath) {
        try {
            Properties prop = new Properties();
            //Idea测试（完成）
            InputStream in = null;
            in = PropertiesReaderUtils.class.getClassLoader().getResourceAsStream(confPath);
            //集群测试
//            String filePath = System.getProperty("user.dir") + confPath;
//            String filePath = confPath;
//            InputStream in = new BufferedInputStream(new FileInputStream(filePath));
            prop.load(in);
            in.close();
            props.put(confPath, prop);
        }catch (Exception e) {
            System.out.println("配置文件读取失败");
            e.printStackTrace();
        }
        return props.get(confPath);
    }
}
