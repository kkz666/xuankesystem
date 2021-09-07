package com.zhangkai.www.xuankesystem.dao.impl;

import com.zhangkai.www.xuankesystem.dao.CurriculumDao;
import com.zhangkai.www.xuankesystem.dao.Stu_CurDao;
import com.zhangkai.www.xuankesystem.dao.UserDao;
import com.zhangkai.www.xuankesystem.domain.Curriculum;
import com.zhangkai.www.xuankesystem.domain.Stu_Cur;
import com.zhangkai.www.xuankesystem.domain.User;
import com.zhangkai.www.xuankesystem.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Stu_CurDaoImpl implements Stu_CurDao {
    UserDao userDao=new UserDaoImpl();
    CurriculumDao curriculumDao=new CurriculumDaoImpl();
    public boolean selectcurriculum(User user, Curriculum curriculum){
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql="insert into stu_cur(s_id,c_id) values(?,?)";
        try{
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, user.getId());
            pstmt.setInt(2, curriculum.getId());
            pstmt.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
            JDBCUtils.close(conn, pstmt);
            return false;
        }
        JDBCUtils.close(conn,pstmt);
        return true;
    }
    public List<Curriculum> findAllSelectCurriculumById(int id){
        List<Curriculum> list = new ArrayList<Curriculum>();
        String sql="SELECT c.* FROM stu_cur AS sc LEFT JOIN USER AS u ON u.id=sc.s_id LEFT JOIN curriculum AS c ON c.id=sc.c_id WHERE sc.s_id= ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try{
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Curriculum cur = new Curriculum();
                int cid = rs.getInt("id");
                int credit = rs.getInt("credit");
                int currentnumber = rs.getInt("currentnumber");
                int limitnumber = rs.getInt("limitnumber");
                String t_name = rs.getString("t_name");
                String c_name = rs.getString("c_name");
                String detailtime = rs.getString("detailtime");
                Date starttime = rs.getDate("starttime");
                Date endtime = rs.getDate("endtime");
                cur.setId(cid);
                cur.setLimitnumber(limitnumber);
                cur.setCurrentnumber(currentnumber);
                cur.setC_name(c_name);
                cur.setT_name(t_name);
                cur.setStarttime(starttime);
                cur.setEndtime(endtime);
                cur.setCredit(credit);
                cur.setDetailtime(detailtime);
                list.add(cur);
            }
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        System.out.println(list);
        return list;
    }
    public boolean TuiKe (int id){
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            String sql = "delete from stu_cur where c_id = ? ";
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
}
