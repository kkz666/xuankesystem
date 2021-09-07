package com.zhangkai.www.xuankesystem.service.impl;

import com.zhangkai.www.xuankesystem.dao.Stu_CurDao;
import com.zhangkai.www.xuankesystem.dao.impl.Stu_CurDaoImpl;
import com.zhangkai.www.xuankesystem.domain.Curriculum;
import com.zhangkai.www.xuankesystem.domain.Stu_Cur;
import com.zhangkai.www.xuankesystem.domain.User;
import com.zhangkai.www.xuankesystem.service.Stu_CurService;

import java.util.List;

public class Stu_CurServiceImpl implements Stu_CurService {
    private Stu_CurDao stu_CurDao=new Stu_CurDaoImpl();
    @Override
    public boolean selectcurriculum(User user, Curriculum curriculum) {
        return stu_CurDao.selectcurriculum(user,curriculum);
    }
    public List<Curriculum> findAllSelectCurriculumById(int id){
        return stu_CurDao.findAllSelectCurriculumById(id);
    }
    public boolean TuiKe(int cid,int uid){
        return stu_CurDao.TuiKe(cid,uid);
    }
}
