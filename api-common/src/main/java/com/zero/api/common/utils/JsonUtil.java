package com.zero.api.common.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonUtil {

    private static final Log log = LogFactory.getLog(JsonUtil.class);

    private static Gson gson = new Gson();

    public static String objectToString(Object paramObject) {
        if (paramObject == null) {
            return null;
        }
        String json = null;
        try {
            json = gson.toJson(paramObject);
        } catch (Exception e) {
            log.error("ObjectToString error:" + e.getMessage());
        }
        return json;
    }

    public static <T> T stringToObject(String paramString, Class<T> paramClass) {
        if (StringUtil.isEmpty(paramString)) {
            return null;
        }
        T t = null;
        try {
            t = gson.fromJson(paramString, paramClass);
        } catch (Exception e) {
            log.error("StringToObject error:" + e.getMessage());
        }
        return t;
    }

    public static <T> T stringToList(String paramString, Type paramType) {
        if (StringUtil.isEmpty(paramString)) {
            return null;
        }
        T t = null;
        try {
            t = gson.fromJson(paramString, paramType);
        } catch (Exception e) {
            log.error("StringToList error:" + e.getMessage());
        }
        return t;
    }

    /**
     * @param json
     * @param clazz
     * @return
     * @author I321533
     */
    public static <T> List<T> jsonToList(String json, Class<T[]> clazz) {
        Gson gson = new Gson();
        T[] array = gson.fromJson(json, clazz);
        return Arrays.asList(array);
    }

    /**
     * @param json
     * @param clazz
     * @return
     */
    public static <T> ArrayList<T> jsonToArrayList(String json, Class<T> clazz) {
        Type type = new TypeToken<ArrayList<JsonObject>>() {
        }.getType();
        ArrayList<JsonObject> jsonObjects = new Gson().fromJson(json, type);

        ArrayList<T> arrayList = new ArrayList<T>();
        for (JsonObject jsonObject : jsonObjects) {
            arrayList.add(new Gson().fromJson(jsonObject, clazz));
        }
        return arrayList;
    }

}