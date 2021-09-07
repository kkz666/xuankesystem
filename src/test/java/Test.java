import com.zhangkai.www.xuankesystem.dao.MyJdbcTemplate;
import com.zhangkai.www.xuankesystem.domain.User;
import com.zhangkai.www.xuankesystem.util.JDBCUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class Test {
    /*@org.junit.Test
    public void findByUsername() throws Exception {
        User user = null;
        user = new User();
        String ans = "kkkkzzzz";
        String sql = "select * from user where username = ?";
        Connection conn = JDBCUtils.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, ans);
        ResultSet rs = pstmt.executeQuery();
        System.out.println(pstmt);
        while (rs.next()) {
            int id = rs.getInt("id");//用户id
            String susername = rs.getString("username");//用户名，账号
            String password = rs.getString("username");//密码
            String name = rs.getString("name");//真实姓名
            String sex = rs.getString("sex");//男或女
            String xuehao = rs.getString("xuehao");
            ;//学号
            String zhuanye = rs.getString("zhuanye");
            ;//专业
            String banji = rs.getString("banji");
            ;//班级
            System.out.println(susername);
            System.out.println(password);
            System.out.println(name);
            System.out.println(sex);
            System.out.println(xuehao);
            user.setUsername(susername);
            user.setAdmin(false);
            user.setBanji(banji);
            user.setName(name);
            user.setPassword(password);
            user.setSex(sex);
            user.setXuehao(xuehao);
            user.setZhuanye(zhuanye);
            user.setId(id);
            System.out.println("----------------");
            System.out.println(user);
        }
    }
*/

    public void findByUsername() {
        User user = null;
    String username="kkkkzzzz";
        try {
            user=new User();
            String sql = "select * from user where username = ?";
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            int id=-1;
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
                user.setId(id);
            }
            System.out.println(user);
            if(id>0){}
        } catch (Exception e) {
            e.printStackTrace();
        }//出异常就返回null
    }

    @org.junit.Test
    public void findByUsernameAndPassword() throws Exception{
        User user = user=new User();;
        String username = "kkkkzzzz";
        String password = "123456789";
            String sql = "select * from user where username = ? and password = ?";
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
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
            user.setId(id);
        }
        System.out.println(user);
    }
    @org.junit.Test
    public void findAll() {
        List<User>list=new ArrayList<User>();
        String sql = "select * from user ";
        try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                User user=new User();
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
                user.setId(id);
                list.add(user);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println(list);
    }
    @org.junit.Test
    public void update() {

        String sex= "男";
        String name= "测试数据";//真实姓名
        String xuehao= "31202346816";//学号
        String zhuanye= "测试数据";//专业
        String banji= "测试班级";//班级
        try {
            String sql = "update user set name= ?,xuehao= ?,zhuanye = ?,banji= ? ,sex= ? where id= ?";
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, xuehao);
            pstmt.setString(3, zhuanye);
            pstmt.setString(4, banji);
            pstmt.setString(5, sex);
            pstmt.setInt(6,1024);
            System.out.println(pstmt.executeUpdate());
        }catch (Exception e){
        }

    }
    @org.junit.Test
    public void daoRu(){
        List<User> list=new ArrayList<>();
        User user1=new User();
        user1.setId(113);
        user1.setUsername("abbavasfas");
        user1.setAdmin(false);
        user1.setBanji("导入测试二");
        user1.setName("导出测试一");
        user1.setPassword("123456789");
        user1.setSex("男");
        user1.setXuehao("46531313");
        user1.setZhuanye("导入测试一");
        list.add(user1);
        User user2=new User();
        user2.setId(114);
        user2.setUsername("abbatetvasfas");
        user2.setAdmin(false);
        user2.setBanji("导入测");
        user2.setName("导出测一");
        user2.setPassword("1456789");
        user2.setSex("男");
        user2.setXuehao("431313");
        user2.setZhuanye("导测试一");
        list.add(user2);
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
        }
        JDBCUtils.close(conn, pstmt);
    }

    @org.junit.Test
    public void readExcel() throws Exception {
        List<User> list=new ArrayList<>();
    String filePath="/xuankesystem/src/main/resources/userReadExcel.xlsx";
        FileInputStream fis = new FileInputStream(
                new File(filePath));
        //打开需要读取的文件
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        //按照SHEET的名称读取一个电子表格
        XSSFSheet sheet = workbook.getSheet("Sheet0");
        //获取一个行的迭代器
        Iterator<Row> rowIterator = sheet.rowIterator();
        Row row=null;//自己添加
        int index=0;
        int yu=index%9;
        User user=null;
        while(rowIterator.hasNext()) {
            System.out.println("外层"+index);
            user=new User();
            row = (XSSFRow) rowIterator.next();
            //获取单元格迭代器
            Iterator<Cell> cellIterator = row.cellIterator();
            while(cellIterator.hasNext()) {
                index++;
                Cell cell = cellIterator.next();
                if(index<10)continue;
                switch(cell.getCellType()) {
                    /*case NUMERIC:
                        double val = cell.getNumericCellValue();
                        System.out.print(val+"\t\t");
                        break ;*/
                    case STRING:
                        String str = cell.getStringCellValue();
                        switch(index%9){
                            case 0:{
                                user.setAdmin(Boolean.getBoolean(str));
                                break;
                            }
                            case 1:{
                                user.setId(Integer.parseInt(str));
                                break;
                            }
                            case 2:{
                                user.setUsername(str);
                                break;
                            }
                            case 3:{
                                user.setPassword(str);
                                break;
                            }
                            case 4:{
                                user.setName(str);
                                break;
                            } case 5:{
                                user.setSex(str);
                                break;
                            }case 6:{
                                user.setXuehao(str);
                                break;
                            }
                            case 7:{
                                user.setZhuanye(str);
                                break;
                            }
                            case 8:{
                                user.setBanji(str);
                                break;
                            }
                        }
                }
            }

            if(index>10) {
                System.out.println(user);
                list.add(user);
            }
        }
        fis.close();
    }
    @org.junit.Test
    public void timetest(){
        Date date=new Date();
        int hours=date.getHours();
        System.out.println(hours);

        GregorianCalendar calendar = new GregorianCalendar();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        System.out.println( hour);
    }
}