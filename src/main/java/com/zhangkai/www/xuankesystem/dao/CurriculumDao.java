package com.zhangkai.www.xuankesystem.dao;

import com.zhangkai.www.xuankesystem.domain.Curriculum;

import java.util.List;

public interface CurriculumDao {
    public void save(Curriculum curriculum);
    public Curriculum findByc_name(String c_name);
    public List<Curriculum> findAll();
    public boolean update(Curriculum curriculum, int id);
    public Curriculum findById(int id);
    public boolean delete(Curriculum curriculum);
    public boolean checkStu_Contains(Curriculum curriculum);
}

