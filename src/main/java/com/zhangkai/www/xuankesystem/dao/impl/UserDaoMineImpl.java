package com.zhangkai.www.xuankesystem.dao.impl;

import com.zhangkai.www.xuankesystem.dao.UserDaoMine;
import com.zhangkai.www.xuankesystem.domain.User;
import com.zhangkai.www.xuankesystem.util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class UserDaoMineImpl implements UserDaoMine {


    public User findByUsername(String username) {
        User user = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            user = new User();
            String sql = "select * from user where username = ?";
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            int id = -1;
            while (rs.next()) {
                id = rs.getInt("id");//用户id
                String susername = rs.getString("username");//用户名，账号
                String password = rs.getString("password");//密码
                String name = rs.getString("name");//真实姓名
                String sex = rs.getString("sex");//男或女
                String xuehao = rs.getString("xuehao");
                String zhuanye = rs.getString("zhuanye");
                String banji = rs.getString("banji");
                boolean admin = rs.getBoolean("admin");
                user.setId(id);
                user.setUsername(susername);
                user.setAdmin(admin);
                user.setBanji(banji);
                user.setName(name);
                user.setPassword(password);
                user.setSex(sex);
                user.setXuehao(xuehao);
                user.setZhuanye(zhuanye);
            }
            if (id > 0) {
            } else return null;
        } catch (Exception e) {
            JDBCUtils.close(conn, pstmt);
            e.printStackTrace();
            return null;
        }//出异常就返回null
        JDBCUtils.close(conn, pstmt);
        return user;
    }

    public void save(User user) {
        String sql = "insert into user (username,password,name,xuehao,zhuanye,banji,sex) values(?,?,?,?,?,?,?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getXuehao());
            pstmt.setString(5, user.getZhuanye());
            pstmt.setString(6, user.getBanji());
            pstmt.setString(7, user.getSex());
            pstmt.executeUpdate();
        } catch (Exception e) {
            JDBCUtils.close(conn, pstmt);
            e.printStackTrace();
        }
        JDBCUtils.close(conn, pstmt);
    }

    public User findByUsernameAndPassword(String username, String password) {
        User user = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            user = new User();
            String sql = "select * from user where username = ? and password = ?";
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            String susername = null;
            while (rs.next()) {
                int id = rs.getInt("id");//用户id
                susername = rs.getString("username");//用户名，账号
                String spassword = rs.getString("username");//密码
                String name = rs.getString("name");//真实姓名
                String sex = rs.getString("sex");//男或女
                String xuehao = rs.getString("xuehao");
                String zhuanye = rs.getString("zhuanye");
                String banji = rs.getString("banji");
                boolean admin = rs.getBoolean("admin");
                user.setId(id);
                user.setUsername(susername);
                user.setAdmin(admin);
                user.setBanji(banji);
                user.setName(name);
                user.setPassword(spassword);
                user.setSex(sex);
                user.setXuehao(xuehao);
                user.setZhuanye(zhuanye);
            }
            if (susername == null) {
                JDBCUtils.close(conn, pstmt);
                return null;
            }
        } catch (Exception e) {
            JDBCUtils.close(conn, pstmt);
            e.printStackTrace();
            return null;
        }//出异常就返回null
        JDBCUtils.close(conn, pstmt);
        return user;
    }

    public List<User> findAll() {
        List<User> list = new ArrayList<User>();
        String sql = "select * from user ";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                User user = new User();
                int id = rs.getInt("id");//用户id
                String susername = rs.getString("username");//用户名，账号
                String spassword = rs.getString("password");//密码
                String name = rs.getString("name");//真实姓名
                String sex = rs.getString("sex");//男或女
                String xuehao = rs.getString("xuehao");
                String zhuanye = rs.getString("zhuanye");
                String banji = rs.getString("banji");
                boolean admin = rs.getBoolean("admin");
                user.setId(id);
                user.setUsername(susername);
                user.setAdmin(admin);
                user.setBanji(banji);
                user.setName(name);
                user.setPassword(spassword);
                user.setSex(sex);
                user.setXuehao(xuehao);
                user.setZhuanye(zhuanye);
                list.add(user);
            }
        } catch (Exception e) {
            JDBCUtils.close(conn, pstmt);
            e.printStackTrace();
        }
        JDBCUtils.close(conn, pstmt);
        return list;
    }

    //未测试
    public boolean delete(User user) {
        int id = user.getId();
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            String sql = "delete  from user where id = ? ";
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            if (pstmt.executeUpdate() == 0) return false;
        } catch (Exception e) {
            JDBCUtils.close(conn, pstmt);
            return false;
        }//出异常就返回null
        JDBCUtils.close(conn, pstmt);
        return true;
    }
    //OK
    public boolean update(User user, int id) {
        String sex = user.getSex();
        String name = user.getName();//真实姓名
        String xuehao = user.getXuehao();//学号
        String zhuanye = user.getZhuanye();//专业
        String banji = user.getBanji();//班级
        PreparedStatement pstmt = null;
        Connection conn = null;
        try {
            String sql = "update user set name= ?,xuehao= ?,zhuanye = ?,banji= ? ,sex= ? where id= ?";
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, xuehao);
            pstmt.setString(3, zhuanye);
            pstmt.setString(4, banji);
            pstmt.setString(5, sex);
            pstmt.setInt(6, id);
            if (pstmt.executeUpdate() == 0) return false;
        } catch (Exception e) {
            JDBCUtils.close(conn, pstmt);
            return false;
        }//出异常就返回null
        JDBCUtils.close(conn, pstmt);
        return true;
    }

    public User findById(int id) {
        User user = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            String sql = "select * from user where id = ? ";
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                user = new User();
                int sid = rs.getInt("id");//用户id
                String susername = rs.getString("username");//用户名，账号
                String spassword = rs.getString("password");//密码
                String name = rs.getString("name");//真实姓名
                String sex = rs.getString("sex");//男或女
                String xuehao = rs.getString("xuehao");
                String zhuanye = rs.getString("zhuanye");
                String banji = rs.getString("banji");
                boolean admin = rs.getBoolean("admin");
                user.setId(sid);
                user.setUsername(susername);
                user.setAdmin(admin);
                user.setBanji(banji);
                user.setName(name);
                user.setPassword(spassword);
                user.setSex(sex);
                user.setXuehao(xuehao);
                user.setZhuanye(zhuanye);
            }
        } catch (Exception e) {
            JDBCUtils.close(conn, pstmt);
            e.printStackTrace();
        }//出异常就返回null
        JDBCUtils.close(conn, pstmt);
        return user;
    }

    public boolean findPower(int id) {
        System.out.println(id);
        User user = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean admin=false;
        try {
            String sql = "select * from user where id = ? ";
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                admin = rs.getBoolean("admin");
            }
        } catch (Exception e) {
            JDBCUtils.close(conn, pstmt);
            e.printStackTrace();
            return false;
        }//出异常就返回null
        JDBCUtils.close(conn, pstmt);
        if(admin==true)
        return true;
        else return false;
    }
    public boolean daoRu(List<User> list){
        String sql = "insert into user (username,password,name,xuehao,zhuanye,banji,sex,id,admin) values(?,?,?,?,?,?,?,?,?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = JDBCUtils.getConnection();
            while(list.size()>0) {
                User user=list.remove(0);
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, user.getUsername());
                pstmt.setString(2, user.getPassword());
                pstmt.setString(3, user.getName());
                pstmt.setString(4, user.getXuehao());
                pstmt.setString(5, user.getZhuanye());
                pstmt.setString(6, user.getBanji());
                pstmt.setString(7, user.getSex());
                pstmt.setInt(8,user.getId());
                pstmt.setBoolean(9,user.getAdmin());
                pstmt.executeUpdate();
            }
        } catch (Exception e) {
            JDBCUtils.close(conn, pstmt);
            e.printStackTrace();
            return false;
        }
        JDBCUtils.close(conn, pstmt);
        return true;
    }
}