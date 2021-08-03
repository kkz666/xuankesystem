package com.example.xuankesystem.xuanke.web.servlet;

import com.example.xuankesystem.xuanke.domain.Curriculum;
import com.example.xuankesystem.xuanke.domain.User;
import com.example.xuankesystem.xuanke.service.CurriculumService;
import com.example.xuankesystem.xuanke.service.UserService;
import com.example.xuankesystem.xuanke.service.impl.CurriculumServiceImpl;
import com.example.xuankesystem.xuanke.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/curriculumEchoServlet")
public class CurriculumEchoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        int id=(Integer)session.getAttribute("CURRICULUMUPDATEID_SERVE");
        Curriculum curriculum =null;
        CurriculumService service=new CurriculumServiceImpl();
        curriculum=service.findById(id);
        //获取原来的数据，用于数据回写
        List<Curriculum> cs=new ArrayList<Curriculum>();
        cs.add(curriculum);
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
