package com.zhangkai.www.xuankesystem.web.servlet;

import com.zhangkai.www.xuankesystem.domain.User;
import com.zhangkai.www.xuankesystem.service.UserService;
import com.zhangkai.www.xuankesystem.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 修改用户信息时填充表格的servlet
 */
@WebServlet("/echoServlet")
public class EchoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        int id=(Integer)session.getAttribute("UPDATEID_SERVE");
        System.out.println(id);
        User user =null;
        UserService service=new UserServiceImpl();
        user=service.findById(id);
        //获取原来的数据，用于数据回写
        List<User> cs=new ArrayList<User>();
        cs.add(user);
        //List<User> cs= service.findAll();
        ObjectMapper mapper =new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),cs);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
