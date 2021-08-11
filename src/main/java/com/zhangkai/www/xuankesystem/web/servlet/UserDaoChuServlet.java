package com.zhangkai.www.xuankesystem.web.servlet;

import com.zhangkai.www.xuankesystem.service.CreateExcel;
import com.zhangkai.www.xuankesystem.service.UserService;
import com.zhangkai.www.xuankesystem.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/userDaoChuServlet")
public class UserDaoChuServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService service=new UserServiceImpl();
        try{
            CreateExcel.createExcelByBeans(service.findAll());
            System.out.println("导出用户列表已执行");
        }catch(Exception e){
            e.printStackTrace();
        }

        request.getRequestDispatcher("/listshow1.html").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    this.doGet(request, response);
    }
}
