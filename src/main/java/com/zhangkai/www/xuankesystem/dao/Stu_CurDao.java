package com.zhangkai.www.xuankesystem.dao;

import com.zhangkai.www.xuankesystem.domain.Curriculum;
import com.zhangkai.www.xuankesystem.domain.Stu_Cur;
import com.zhangkai.www.xuankesystem.domain.User;

import java.util.List;

public interface Stu_CurDao {
    public boolean selectcurriculum(User user, Curriculum curriculum);
    public List<Curriculum> findAllSelectCurriculumById(int id);
    public boolean TuiKe (int id);
}
