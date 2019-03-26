package Api.utils;

import com.sun.jersey.core.util.Base64;

public class CompareFeatureUtils {
    public static double compare(int typeObject, String feature1, String feature2) {
        return CompareFeatureJava.compareFeatures(typeObject, Base64.decode(feature1.getBytes()), Base64.decode(feature2.getBytes()));
    }
}
