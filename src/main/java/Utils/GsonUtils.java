package Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * # -*- coding:utf-8 -*- #
 * 作者:30671
 * 日期:2022年03月26日11时46分
 */
public class GsonUtils {
    /**
     * GSON         对象过滤空值
     * GSON_NULL    对象空值也进行转化
     */
    private static final Gson GSON;
    private static final Gson GSON_NULL;

    /**
     * 枚举
     *      过滤掉空字符串
     */
    public enum GsonSerializerFeature {
        WriteMapNullValue
    }

    /**
     * 静态代码块
     * 自动创建对象
     */
    static {
        GSON = new GsonBuilder().enableComplexMapKeySerialization() //当Map的key为复杂对象时,需要开启该方法
                // .serializeNulls() //当字段值为空或null时，依然对该字段进行转换
                // .excludeFieldsWithoutExposeAnnotation()//打开Export注解，但打开了这个注解,副作用，要转换和不转换都要加注解
                .setDateFormat("yyyy-MM-dd HH:mm:ss")//序列化日期格式  "yyyy-MM-dd"
                // .setPrettyPrinting() //自动格式化换行
                .disableHtmlEscaping() //防止特殊字符出现乱码
                .create();
        GSON_NULL = new GsonBuilder().enableComplexMapKeySerialization() //当Map的key为复杂对象时,需要开启该方法
                .serializeNulls() //当字段值为空或null时，依然对该字段进行转换
                // .excludeFieldsWithoutExposeAnnotation()//打开Export注解，但打开了这个注解,副作用，要转换和不转换都要加注解
                .setDateFormat("yyyy-MM-dd HH:mm:ss")//序列化日期格式  "yyyy-MM-dd"
                // .setPrettyPrinting() //自动格式化换行
                .disableHtmlEscaping() //防止特殊字符出现乱码
                .create();
    }

    //获取gson解析器
    public static Gson getGSON() {
        return GSON;
    }

    //获取gson解析器 有空值 解析
    public static Gson getGSON_NULL() {
        return GSON_NULL;
    }

    /**
     * List :
     *       是存储 单列数据 的集合           值Object
     *       存储的数据是有顺序，并且允许重复
     * Map :
     *       是存储 键 和 值 的双列数据的集合  键String 值Object
     *       Map中存储的数据是没有顺序的，其键是不能重复的，它的值是可以有重复的
     */

    /**
     * ---------------------------------------------------------------------------
     *  对象 --> json
     *  返回对象类型可通过 返回值的T来确定
     *
     *  objectToJsonString object  ->  json
     *  listToJsonString List<T>  ->  json
     *  mapToJsonString Map<String, T>  ->  json
     *  listMapToJsonString List<Map<String, T>>  ->  json
     */
    public static String objectToJsonString(Object object) {
        return GSON.toJson(object);
    }
    public static String objectToJsonString(Object object, GsonSerializerFeature gsonSerializerFeature) {
        if (gsonSerializerFeature == GsonSerializerFeature.WriteMapNullValue){
            return GSON_NULL.toJson(object);
        }
        return GSON.toJson(object);
    }
    public static <T>  String listToJsonString(List<T> objectList) {
        return GSON.toJson(objectList);
    }
    public static <T> String listToJsonString(List<T> objectList, GsonSerializerFeature gsonSerializerFeature) {
        if (gsonSerializerFeature == GsonSerializerFeature.WriteMapNullValue){
            return GSON_NULL.toJson(objectList);
        }
        return GSON.toJson(objectList);
    }
    public static <T> String mapToJsonString(Map<String, T> objectMap) {
        return GSON.toJson(objectMap);
    }
    public static <T> String mapToJsonString(Map<String, T>objectMap, GsonSerializerFeature gsonSerializerFeature) {
        if (gsonSerializerFeature == GsonSerializerFeature.WriteMapNullValue){
            return GSON_NULL.toJson(objectMap);
        }
        return GSON.toJson(objectMap);
    }

    /**
     * ---------------------------------------------------------------------------
     *  Json --> 对象(T)
     *  返回对象类型可通过 返回值的T来确定
     *
     *  strToJavaBeanMaps json  ->  T
     *  strToJavaBeanList json  ->  List<T>
     *  strToJavaBeanMaps json  ->  Map<String,T>
     *  strToJavaBeanListMaps json  ->  List<Map<String,T>>
     */
    public static <T> T strToJavaBean(String gsonString) {
        return GSON.fromJson(gsonString, new TypeToken<T>() {}.getType());
    }
    public static <T> List<T> strToJavaBeanList(String gsonString) {
        return GSON.fromJson(gsonString, new TypeToken<List<T>>() {}.getType());
    }
    public static <T> Map<String, T> strToJavaBeanMaps(String gsonString) {
        return GSON.fromJson(gsonString, new TypeToken<Map<String, T>>() {}.getType());
    }
    public static <T> List<Map<String, T>> strToJavaBeanListMaps(String gsonString) {
        return GSON.fromJson(gsonString, new TypeToken<List<Map<String, T>>>() {}.getType());
    }
}
