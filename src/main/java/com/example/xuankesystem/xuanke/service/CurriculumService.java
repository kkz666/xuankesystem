package com.example.xuankesystem.xuanke.service;

import com.example.xuankesystem.xuanke.domain.Curriculum;
import com.example.xuankesystem.xuanke.domain.User;

import java.util.List;

public interface CurriculumService {
    boolean regist(Curriculum curriculum);
    public List<Curriculum> findAll();
    public boolean update(Curriculum curriculum,int id);
    public Curriculum findById(int id);
    public boolean delete(Curriculum curriculum);
}
