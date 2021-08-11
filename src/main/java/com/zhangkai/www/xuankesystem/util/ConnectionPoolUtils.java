package com.zhangkai.www.xuankesystem.util;

import com.zhangkai.www.xuankesystem.dao.ConnectionPool;

public class ConnectionPoolUtils {
    /*连接池工具类，返回唯一的一个数据库连接池对象,单例模式*/
        private ConnectionPoolUtils(){};//私有静态方法
        private static ConnectionPool poolInstance = null;
        public static ConnectionPool GetPoolInstance(){
            if(poolInstance == null) {
                poolInstance = new ConnectionPool(
                        "com.mysql.jdbc.Driver",
                        "jdbc:mysql://localhost:3306/world?useUnicode=true&characterEncoding=utf-8",
                        "root", "009683");
                try {
                    poolInstance.createPool();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            return poolInstance;
        }
}
