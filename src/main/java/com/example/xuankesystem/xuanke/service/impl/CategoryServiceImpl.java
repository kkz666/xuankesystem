package com.example.xuankesystem.xuanke.service.impl;

import com.example.xuankesystem.xuanke.dao.CategoryDao;
import com.example.xuankesystem.xuanke.dao.impl.CategoryDaoImpl;
import com.example.xuankesystem.xuanke.domain.Category;
import com.example.xuankesystem.xuanke.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categorydao=new CategoryDaoImpl();
    @Override
    public List<Category> findAll() {
        return categorydao.findAll();
    }
}
