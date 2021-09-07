package com.zhangkai.www.xuankesystem.dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MyJdbcTemplate {
    private DataSource mDataSource;

    public MyJdbcTemplate(DataSource dataSource) {
        this.mDataSource = dataSource;
    }
    public int update(String sql, Object... args) {
        int updateCount = 0;
        //程序执行可能会发生异常，故我们使用try-catch进行对异常的处理
        try {
            //获取连接
            Connection connection = mDataSource.getConnection();
            //预编译对象
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //获取元数据，即数据库表的字段
            ParameterMetaData parameterMetaData = preparedStatement.getParameterMetaData();
            //通过ParameterMetaData中的getParameterCount()方法，可以获取sql语句中？的个数，即可以确定所需参数的个数
            int parameterCount = parameterMetaData.getParameterCount();
            //通过for循环去动态设置所需参数
            for (int i = 0; i < parameterCount; i++) {
                //setObject()方法一般只会在写框架底层中使用
                preparedStatement.setObject(i + 1, args[i]);
            }
            //通过preparedStatement去执行executeUpdate()方法返回受影响的行数
            updateCount = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //最后方法返回一个int类型数据，将受影响的行数返回
        return updateCount;
    }
}