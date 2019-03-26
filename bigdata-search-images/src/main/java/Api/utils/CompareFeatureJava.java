package Api.utils;

public class CompareFeatureJava {

    private static CompareFeatureJava api = new CompareFeatureJava();

    private CompareFeatureJava()
    {
        super();
    }

    public static double compareFeatures(int type, byte[] feature1, byte[] feature2)
    {
        return api.compare(type, feature1, feature2);
    }

    private float compute(byte[] feature1, int start1, byte[] feature2, int start2, int length, boolean isDist)
    {
        int sz = length / 4;
        float score = 0.0f;
        for (int i = 0; i < sz; i++)
        {
            score += byte2Float(feature1, start1 + i * 4) * byte2Float(feature2, start2 + i * 4);
        }
        score = (1.0f - score) / 2.0f;
        if (isDist)
        {
            return score;
        }
        return (1.0f - score);
    }

    public float compare(int type, byte[] feature1, byte[] feature2)
    {
        int featureStartIndex1 = getStartIndex(type, feature1.length);
        int featureStartIndex2 = getStartIndex(type, feature2.length);
        if (featureStartIndex1 < 0 || featureStartIndex2 < 0)
            return 1.0f;
        int featureLen = 6144;
        if (type == 3) featureLen = 2048;
        if (type == 2 && feature1.length < 1000) featureLen = 512;

        return compute(feature1, featureStartIndex1, feature2, featureStartIndex2, featureLen, true);
    }

    private int getStartIndex(int type, int featureLen)
    {
        switch (type)
        {
            case 1:
            case 4:
                if (6144 == featureLen)
                    return 0;
                else if (8240 == featureLen || 6192 == featureLen)
                    return 48;
            case 3:
                if (2048 == featureLen)
                    return 0;
                else if (2096 == featureLen)
                    return 48;
                else if (8240 == featureLen)
                    return 6192;
            case 2:
                if (6144 == featureLen || 512 == featureLen)
                    return 0;
                else if (528 == featureLen)
                    return 16;
                else if (8240 == featureLen || 6192 == featureLen)
                    return 48;
        }
        return -1;
    }

    //这个函数将byte转换成float
    private float byte2Float(byte[] b, int index)
    {
        int l;
        l = b[index + 0];
        l &= 0xff;
        l |= ((long) b[index + 1] << 8);
        l &= 0xffff;
        l |= ((long) b[index + 2] << 16);
        l &= 0xffffff;
        l |= ((long) b[index + 3] << 24);
        return Float.intBitsToFloat(l);
    }
}
