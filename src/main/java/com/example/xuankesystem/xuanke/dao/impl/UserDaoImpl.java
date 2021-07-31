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
        String username= user.getUsername();
        try {
            String sql = "delete  from user where username = ? ";
            template.update(sql,username);
        }catch (Exception e){
            return false;
        }//出异常就返回null
        return true;
    }
    public boolean update(User user){
        return false;
    }
    public int findId(User user){
        String sql = "select * from user where username = ? and password = ?";
        user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), user.getUsername(),user.getPassword());
        System.out.println(user);
        return user.getUid();
    }
}
