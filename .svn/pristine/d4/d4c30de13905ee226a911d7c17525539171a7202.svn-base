package com.qst.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by hkl on 7/25/18.
 */
public class PropertiesUtils {

    public static Properties load(String path) throws IOException{

        InputStream in = PropertiesUtils.class.getClassLoader().getResourceAsStream(path);
        Properties p = new Properties();
        p.load(new InputStreamReader(in));
        return p;
    }

}
