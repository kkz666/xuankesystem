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

    /**
     * 更新课程入选学生数量
     * @param curriculum
     * @return
     */
    public boolean updatecurriculumnumber(Curriculum curriculum){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        int id=curriculum.getId();
        int currentnumber=curriculum.getCurrentnumber();
        int limitnumber=curriculum.getLimitnumber();
        if(limitnumber==currentnumber)return false;
        String sql="update curriculum set currentnumber= ? where id= ?";
        try {
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, currentnumber+1);
            pstmt.setInt(2, id);
            if (pstmt.executeUpdate() == 0){
                JDBCUtils.close(conn, pstmt);
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            JDBCUtils.close(conn, pstmt);
            return false;
        }
        JDBCUtils.close(conn, pstmt);
        return true;
    }

    /**
     * 检查该学生是否已选该课程
     * @param user
     * @param curriculum
     * @return
     */
    public boolean checkcontains(User user,Curriculum curriculum){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        int uid=user.getId();
        int cid=curriculum.getId();
        String sql="select * from stu_cur where s_id= ? and c_id= ?";
        try{
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, user.getId());
            pstmt.setInt(2, curriculum.getId());
            rs=pstmt.executeQuery();
            if(!rs.next()) {//没有查到数据返回true
                JDBCUtils.close(conn, pstmt);
                return true;
            }
            else{
                JDBCUtils.close(conn, pstmt);
                return false;//有查到数据返回false
            }
        }catch (Exception e) {
            e.printStackTrace();
            JDBCUtils.close(conn, pstmt);
            return false;
        }
    }

    /**
     * 选课
     * @param user
     * @param curriculum
     * @return
     */
    public boolean selectcurriculum(User user, Curriculum curriculum){
        if(!checkcontains(user,curriculum)){
            return false;
        }
        if(!updatecurriculumnumber(curriculum)){
            return false;
        }
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

    /**
     * 找到学生选中课程的信息通过学生id
     * @param id
     * @return
     */
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
            JDBCUtils.close(conn, pstmt);
            return null;
        }
        JDBCUtils.close(conn, pstmt);
        return list;
    }

    /**
     * 退课
     * @param cid
     * @param uid
     * @return
     */
    public boolean TuiKe (int cid,int uid){
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            String sql = "delete from stu_cur where c_id = ? and s_id= ?";
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, cid);
            pstmt.setInt(2, uid);
            System.out.println("c_id="+cid);
            System.out.println("s_id="+uid);
            if (pstmt.executeUpdate() == 0){
                JDBCUtils.close(conn, pstmt);
                return false;
            }
        } catch (Exception e) {
            JDBCUtils.close(conn, pstmt);
            return false;
        }//出异常就返回null
        JDBCUtils.close(conn, pstmt);
        return true;
    }
}
