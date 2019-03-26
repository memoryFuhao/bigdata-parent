package com.qst.utils;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @author hkl
 * @date 2019/3/22
 */
public class HdfsPropertiesUtils {

    static private Logger logger = LoggerFactory.getLogger(HdfsPropertiesUtils.class);

    public static Properties load(String strPath) throws IOException {

        Properties prop = PropertiesUtils.load("conf/path.properties");
        String hdfs = prop.getProperty("hdfspath");

        Configuration conf = new Configuration();
        conf.set("fs.defaultFS",hdfs);
        FileSystem fileSystem = FileSystem.get(conf);
        Path path = new Path(strPath);
        InputStream in = fileSystem.open(path);
        Properties p = new Properties();
        p.load(new InputStreamReader(in));
        return p;
    }

}
