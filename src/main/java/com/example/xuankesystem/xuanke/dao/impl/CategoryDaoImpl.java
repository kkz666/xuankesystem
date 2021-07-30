package com.example.xuankesystem.xuanke.dao.impl;

import com.example.xuankesystem.xuanke.dao.CategoryDao;
import com.example.xuankesystem.xuanke.domain.Category;
import com.example.xuankesystem.xuanke.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Category> findAll() {
    String sql="select * from user ";

        return template.query(sql,new BeanPropertyRowMapper<Category>(Category.class));
    }
}
