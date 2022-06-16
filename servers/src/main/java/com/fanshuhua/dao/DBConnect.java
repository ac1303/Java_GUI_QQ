package com.fanshuhua.dao;

import com.fanshuhua.properties.PropertiesUtil;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author 范书华
 * @create 2022/6/16 14:45
 */
public class DBConnect {
//    获取数据库连接
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(PropertiesUtil.get("url"), PropertiesUtil.get("user"), PropertiesUtil.get("password"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
