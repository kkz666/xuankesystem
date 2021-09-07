package com.zhangkai.www.xuankesystem.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhangkai.www.xuankesystem.domain.Curriculum;
import com.zhangkai.www.xuankesystem.domain.User;
import com.zhangkai.www.xuankesystem.service.Stu_CurService;
import com.zhangkai.www.xuankesystem.service.UserService;
import com.zhangkai.www.xuankesystem.service.impl.Stu_CurServiceImpl;
import com.zhangkai.www.xuankesystem.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/stu_CurSelectEchoServlet")
public class Stu_CurSelectEchoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        User user=(User)session.getAttribute("user");
        int id=user.getId();
        //获取学生id
        Stu_CurService service=new Stu_CurServiceImpl();
        List<Curriculum> cs= service.findAllSelectCurriculumById(id);
        ObjectMapper mapper =new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),cs);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
