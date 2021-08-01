package com.example.xuankesystem.xuanke.dao.impl;

import com.example.xuankesystem.xuanke.dao.UserDao;
import com.example.xuankesystem.xuanke.domain.User;
import com.example.xuankesystem.xuanke.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate template =new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public User findByUsername(String username) {
        User user =null;
        try {
            String sql = "select * from user where username = ?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
        }catch (Exception e){
        }//出异常就返回null
        return user;
    }

    @Override
    public void save(User user) {
        String sql="insert into user (username,password,name,xuehao,zhuanye,banji,sex) values(?,?,?,?,?,?,?)";
        template.update(sql,user.getUsername(),user.getPassword(),user.getName(),user.getXuehao(),user.getZhuanye(),user.getBanji(),user.getSex());
    }
    @Override
    public User findByUsernameAndPassword(String username,String password){
        User user =null;
        try {
            String sql = "select * from user where username = ? and password = ?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username,password);
        }catch (Exception e){
        }//出异常就返回null
        return user;
    }

    public List<User> findAll() {
        String sql = "select * from user ";
        return template.query(sql,new BeanPropertyRowMapper<User>(User.class));
    }
    public boolean delete(User user){
        int id= user.getId();
        try {
            String sql = "delete  from user where id = ? ";
            template.update(sql,id);
        }catch (Exception e){
            return false;
        }//出异常就返回null
        return true;
    }
    public boolean update(User user,int id){
        String sex= user.getSex();
        String name= user.getName();//真实姓名
        String xuehao= user.getXuehao();//学号
        String zhuanye= user.getZhuanye();//专业
        String banji= user.getBanji();//班级
        try {
            String sql = "update user set name= ?,xuehao= ?,zhuanye = ?,banji= ? ,sex= ? where id= ?";
            template.update(sql,name,xuehao,zhuanye,banji,sex,id);
        }catch (Exception e){
            return false;
        }//出异常就返回null
        return true;
    }
    public User findById(int id){
        User user =null;
        try {
            String sql = "select * from user where id = ? ";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), id);
        }catch (Exception e){
        }//出异常就返回null
        return user;
    }
}
