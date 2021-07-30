package com.example.xuankesystem.xuanke.dao;


import com.example.xuankesystem.xuanke.domain.Category;

import java.util.List;

public interface CategoryDao {
    /**
     * 查询所以
     * @return
     */
    public List<Category> findAll();
}
