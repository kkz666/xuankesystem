package com.zhangkai.www.xuankesystem.dao.impl;

import com.zhangkai.www.xuankesystem.dao.CurriculumDao;
import com.zhangkai.www.xuankesystem.domain.Curriculum;
import com.zhangkai.www.xuankesystem.domain.User;
import com.zhangkai.www.xuankesystem.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.List;

public class CurriculumDaoImpl implements CurriculumDao {
    private JdbcTemplate template =new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 保存课程信息
     * @param curriculum
     */
    public void save(Curriculum curriculum){
        String sql="insert into curriculum (c_name,t_name,credit,limitnumber,currentnumber,detailtime,starttime,endtime) values(?,?,?,?,?,?,?,?)";
        template.update(sql,curriculum.getC_name(),curriculum.getT_name(),curriculum.getCredit(),curriculum.getLimitnumber(),curriculum.getCurrentnumber(),curriculum.getDetailtime(),curriculum.getStarttime(),curriculum.getEndtime());

    }

    /**
     * 通过课程名称查找课程
     * @param c_name
     * @return
     */
    @Override
    public Curriculum findByc_name(String c_name){
        Curriculum curriculum =null;
        try {
            String sql = "select * from curriculum where c_name = ?";
            curriculum = template.queryForObject(sql, new BeanPropertyRowMapper<Curriculum>(Curriculum.class), c_name);
        }catch (Exception e){
        }//出异常就返回null
        return curriculum;
    }

    /**
     * 返回所有课程信息
     * @return
     */
    @Override
    public List<Curriculum> findAll(){
        String sql = "select * from curriculum ";
        return template.query(sql,new BeanPropertyRowMapper<Curriculum>(Curriculum.class));
    }

    /**
     * 更新课程信息
     * @param curriculum
     * @param id
     * @return
     */
    public boolean update(Curriculum curriculum, int id){
        int credit= curriculum.getCredit();
        String c_name= curriculum.getC_name();
        String t_name= curriculum.getT_name();
        Date starttime= curriculum.getStarttime();
        Date endtime= curriculum.getEndtime();
        int limitnumber= curriculum.getLimitnumber();
        int currentnumber= curriculum.getCurrentnumber();
        String detailtime= curriculum.getDetailtime();
        try {
            String sql = "update curriculum set c_name= ?,t_name= ?,starttime = ?,endtime= ? ,limitnumber= ?,currentnumber = ?,detailtime = ?,credit = ? where id= ?";
            template.update(sql,curriculum.getC_name(),curriculum.getT_name(),curriculum.getStarttime(),curriculum.getEndtime(),curriculum.getLimitnumber(),curriculum.getCurrentnumber(),curriculum.getDetailtime(),curriculum.getCredit(),id);
        }catch (Exception e){
            return false;
        }//出异常就返回null
        return true;
    }

    /**
     * 通过id找课程
     * @param id
     * @return
     */
    public Curriculum findById(int id){
        Curriculum curriculum =null;
        try {
            String sql = "select * from curriculum where id = ? ";
            curriculum = template.queryForObject(sql, new BeanPropertyRowMapper<Curriculum>(Curriculum.class), id);
        }catch (Exception e){
        }//出异常就返回null
        return curriculum;
    }

    /**
     * 删除课程
     * @param curriculum
     * @return
     */
    public boolean delete(Curriculum curriculum){
        if(!checkStu_Contains(curriculum)){
            return false;
        }
        int id= curriculum.getId();
        try {
            String sql = "delete  from curriculum where id = ? ";
            template.update(sql,id);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }//出异常就返回null
        return true;
    }

    /**
     * 检查删除的课程是否有学生选中，如果有删除失败
     * @param curriculum
     * @return
     */
    public boolean checkStu_Contains(Curriculum curriculum){
        int id=curriculum.getId();
        List<Curriculum>list=null;
        try {
            String sql = "select * from stu_cur where c_id = ?";
            list=template.query(sql,new BeanPropertyRowMapper<Curriculum>(Curriculum.class),curriculum.getId());
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        if(list==null||list.size()==0){
            return true;//存在选中要删除课程的人
        }else return false;//不存在选中要删除课程的人
    }
}
