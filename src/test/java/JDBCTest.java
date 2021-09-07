import com.zhangkai.www.xuankesystem.dao.CurriculumDao;
import com.zhangkai.www.xuankesystem.dao.UserDao;
import com.zhangkai.www.xuankesystem.dao.impl.CurriculumDaoImpl;
import com.zhangkai.www.xuankesystem.dao.impl.UserDaoImpl;
import com.zhangkai.www.xuankesystem.domain.Curriculum;
import com.zhangkai.www.xuankesystem.domain.User;
import com.zhangkai.www.xuankesystem.util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JDBCTest {
    @Test
    public void checkcontains(){
        UserDao userDao=new UserDaoImpl();
        CurriculumDao curriculumDao=new CurriculumDaoImpl();
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql="select * from stu_cur where s_id= 7 and c_id= 58";
        try{
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            ResultSet rs=pstmt.executeQuery();
            System.out.println(rs);
            System.out.println(rs.next());
        }catch (Exception e) {
            e.printStackTrace();
            JDBCUtils.close(conn, pstmt);
        }
    }
}
