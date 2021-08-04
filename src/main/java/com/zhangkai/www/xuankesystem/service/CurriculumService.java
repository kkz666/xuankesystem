package com.zhangkai.www.xuankesystem.service;

import com.zhangkai.www.xuankesystem.domain.Curriculum;

import java.util.List;

public interface CurriculumService {
    boolean regist(Curriculum curriculum);
    public List<Curriculum> findAll();
    public boolean update(Curriculum curriculum,int id);
    public Curriculum findById(int id);
    public boolean delete(Curriculum curriculum);
}
