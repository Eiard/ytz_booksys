package Utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * # -*- coding:utf-8 -*- #
 * 作者:30671
 * 日期:2021年10月17日23时19分
 */
public class JdbcUtils {

    private static DataSource dataSource ;

    /**
     * 文件的读取，只需要读取一次即可拿到这些值。使用静态代码块
     * 从文件中读取信息
     * 然后进行Utils的属性初始化
     */
    //静态代码块初始化DataSource相关配置信息
    static{
        try {
            //1.加载配置文件
            Properties druidProperties = new Properties();
            druidProperties.load(JdbcUtils.class.getClassLoader().getResourceAsStream("druid.properties"));
            //2.获取DataSource
            dataSource = DruidDataSourceFactory.createDataSource(druidProperties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DataSource getDataSource(){
        return  dataSource;
    }
}
