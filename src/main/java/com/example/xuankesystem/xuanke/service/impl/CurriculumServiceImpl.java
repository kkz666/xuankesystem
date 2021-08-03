package com.example.xuankesystem.xuanke.service.impl;

import com.example.xuankesystem.xuanke.dao.CurriculumDao;
import com.example.xuankesystem.xuanke.dao.impl.CurriculumDaoImpl;
import com.example.xuankesystem.xuanke.domain.Curriculum;
import com.example.xuankesystem.xuanke.domain.User;
import com.example.xuankesystem.xuanke.service.CurriculumService;

import java.util.List;

public class CurriculumServiceImpl implements CurriculumService {
    private CurriculumDao curriculumDao=new CurriculumDaoImpl();
    public boolean regist(Curriculum curriculum){
        //1.根据用户名查询用户对象
        Curriculum c= curriculumDao.findByc_name(curriculum.getC_name());
        //2.保存用户信息
        if(c!=null){//用户名已存在注册失败
            return false;
        }
        curriculumDao.save(curriculum);
        return true;
    }
    public List<Curriculum> findAll(){
        return curriculumDao.findAll();
    }
    public boolean update(Curriculum curriculum,int id){
        if(curriculum==null){
            return false;
        }else{
            return curriculumDao.update(curriculum,id);
        }
    }
    public Curriculum findById(int id){
        Curriculum c=curriculumDao.findById(id);
        return c;
    }
    public boolean delete(Curriculum curriculum){
        if(curriculum==null){
            return false;
        }else {
            curriculumDao.delete(curriculum);
            return true;
        }
    }
}
