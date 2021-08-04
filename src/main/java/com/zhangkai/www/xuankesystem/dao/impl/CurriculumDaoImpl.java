package com.zhangkai.www.xuankesystem.dao.impl;

import com.zhangkai.www.xuankesystem.dao.CurriculumDao;
import com.zhangkai.www.xuankesystem.domain.Curriculum;
import com.zhangkai.www.xuankesystem.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.List;

public class CurriculumDaoImpl implements CurriculumDao {
    private JdbcTemplate template =new JdbcTemplate(JDBCUtils.getDataSource());
    public void save(Curriculum curriculum){
        String sql="insert into curriculum (c_name,t_name,credit,limitnumber,currentnumber,detailtime,starttime,endtime) values(?,?,?,?,?,?,?,?)";
        template.update(sql,curriculum.getC_name(),curriculum.getT_name(),curriculum.getCredit(),curriculum.getLimitnumber(),curriculum.getCurrentnumber(),curriculum.getDetailtime(),curriculum.getStarttime(),curriculum.getEndtime());
    }
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
    @Override
    public List<Curriculum> findAll(){
        String sql = "select * from curriculum ";
        return template.query(sql,new BeanPropertyRowMapper<Curriculum>(Curriculum.class));
    }
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
    public Curriculum findById(int id){
        Curriculum curriculum =null;
        try {
            String sql = "select * from curriculum where id = ? ";
            curriculum = template.queryForObject(sql, new BeanPropertyRowMapper<Curriculum>(Curriculum.class), id);
        }catch (Exception e){
        }//出异常就返回null
        return curriculum;
    }
    public boolean delete(Curriculum curriculum){
        int id= curriculum.getId();
        try {
            String sql = "delete  from curriculum where id = ? ";
            template.update(sql,id);
        }catch (Exception e){
            return false;
        }//出异常就返回null
        return true;
    }
}
