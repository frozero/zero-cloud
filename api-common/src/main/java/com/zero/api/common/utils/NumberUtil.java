package com.zero.api.common.utils;


import org.apache.commons.lang3.math.NumberUtils;

public class NumberUtil {
    public static float getFloat(Object paramObject) {
        return NumberUtils.toFloat(StringUtil.getString(paramObject));
    }

    public static long getLong(Object paramObject) {
        return NumberUtils.toLong(StringUtil.getString(paramObject));
    }

    public static double getDouble(Object paramObject) {
        return NumberUtils.toDouble(StringUtil.getString(paramObject));
    }

    public static int getInt(Object paramObject) {
        String str = StringUtil.getString(paramObject);
        if (str.contains(".")) {
            str = str.split("\\.")[0];
        }
        return NumberUtils.toInt(str);
    }

    public static float getFloat(Object paramObject, float paramFloat) {
        String str = StringUtil.getString(paramObject);
        if (str.contains(".")) {
            str = str.split("\\.")[0];
        }
        return NumberUtils.toFloat(StringUtil.getString(paramObject), paramFloat);
    }

    public static long getLong(Object paramObject, long paramLong) {
        return NumberUtils.toLong(StringUtil.getString(paramObject), paramLong);
    }

    public static double getDouble(Object paramObject, double paramDouble) {
        return NumberUtils.toDouble(StringUtil.getString(paramObject), paramDouble);
    }

    public static int getInt(Object paramObject, int paramInt) {
        return NumberUtils.toInt(StringUtil.getString(paramObject), paramInt);
    }

    public static boolean isNumeric(String paramString) {
        return NumberUtils.isNumber(paramString);
    }

    public static boolean isNotNumeric(String paramString) {
        return !NumberUtils.isNumber(paramString);
    }
}