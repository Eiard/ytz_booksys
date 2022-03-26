package Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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


}
