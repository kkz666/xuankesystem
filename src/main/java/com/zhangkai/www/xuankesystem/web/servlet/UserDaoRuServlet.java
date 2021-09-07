package com.zhangkai.www.xuankesystem.web.servlet;

import com.zhangkai.www.xuankesystem.domain.User;
import com.zhangkai.www.xuankesystem.service.CreateExcel;
import com.zhangkai.www.xuankesystem.service.UserService;
import com.zhangkai.www.xuankesystem.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/userDaoRuServlet")
public class UserDaoRuServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService service=new UserServiceImpl();
        boolean flag=false;
        System.out.println("前戏");
        try{
            List<User> list=CreateExcel.readExcel("/xuankesystem/src/main/resources/ReadExcel.xlsx");
            flag=service.daoRu(list);
            System.out.println("导入用户列表已执行");
        }catch(Exception e){
            e.printStackTrace();
        }
        if(flag==false){
            System.out.println("导入失败");
        }else{
            System.out.println("导入成功");
            request.getRequestDispatcher("/listshow1.html").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
