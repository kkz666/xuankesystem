package com.example.xuankesystem.xuanke.dao;

import com.example.xuankesystem.xuanke.domain.Curriculum;
import com.example.xuankesystem.xuanke.domain.User;

import java.util.List;

public interface CurriculumDao {
    public void save(Curriculum curriculum);
    public Curriculum findByc_name(String c_name);
    public List<Curriculum> findAll();
    public boolean update(Curriculum curriculum, int id);
    public Curriculum findById(int id);
    public boolean delete(Curriculum curriculum);
}

