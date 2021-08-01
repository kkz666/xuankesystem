package com.example.xuankesystem.xuanke.web.servlet;

import com.example.xuankesystem.xuanke.domain.User;
import com.example.xuankesystem.xuanke.service.UserService;
import com.example.xuankesystem.xuanke.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

/**
 * 列表展示用户信息的servlet
 */
@WebServlet("/categoryServlet")
public class CategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService service=new UserServiceImpl();
        List<User> cs= service.findAll();
        ObjectMapper mapper =new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),cs);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    this.doGet(request,response);
    }
}
