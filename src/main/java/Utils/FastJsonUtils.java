package Utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.JSONLibDataFormatSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;
import java.util.Map;

/**
 * # -*- coding:utf-8 -*- #
 * 作者:30671
 * 日期:2022年04月16日17时12分
 */
public class FastJsonUtils {
    private static final SerializeConfig config;
    /**
     * 输出空值字段
     * 如果数组结果为null,则输出为[],而不是null
     * 数值字段为null,则输出为0,而不是null
     * Boolean字段为null,则输出为false,而不是null
     * 字符类型如果为null,则输出为" ",而不是null
     */
    private static final SerializerFeature[] features = {
            SerializerFeature.WriteMapNullValue,
            SerializerFeature.WriteNullListAsEmpty,
            SerializerFeature.WriteNullNumberAsZero,
            SerializerFeature.WriteNullBooleanAsFalse,
            SerializerFeature.WriteNullStringAsEmpty
    };

    static {
        config = new SerializeConfig();
        // 使用和json-lib兼容的日期输出格式
        config.put(java.util.Date.class, new JSONLibDataFormatSerializer());
        // 使用和json-lib兼容的日期输出格式
        config.put(java.sql.Date.class, new JSONLibDataFormatSerializer());
    }

    /**
     * 避免实例化
     */
    private FastJsonUtils() {

    }


    /**
     * ---------------------------------------------------------------------------------
     * 将一个Object装换为Json字符串
     */
    public static <T> String objectToJsonString(T object) {
        return JSONObject.toJSONString(object, config, features);
    }

    /**
     * 将List<对象> 转化为json
     */
    public static <T> String listToJsonString(List<T> objectList) {
        return JSONObject.toJSONString(objectList, config, features);
    }

    /**
     * Map<String,对象> 转化为json
     */
    public static <T> String mapToJsonString(Map<String, T> objectMap) {
        return JSONObject.toJSONString(objectMap, config, features);
    }

    /**
     * ---------------------------------------------------------------------------------
     * 将Json字符串转换为实例对象
     */
    public static <T> T strToJavaBean(String str, TypeReference<T> typeReference) {
        return JSON.parseObject(str, typeReference);
    }

    /**
     * 将Json转换为指定类型的List
     */
    public static <T> List<T> strToJavaBeanList(String str, TypeReference<List<T>> typeReference) {
        return JSON.parseObject(str, typeReference);
    }

    /**
     * 将Json转换为Map
     */
    public static <T> Map<String, T> strToJavaBeanMap(String str, TypeReference<Map<String, T>> typeReference) {
        return JSONObject.parseObject(str, typeReference);
    }
}
