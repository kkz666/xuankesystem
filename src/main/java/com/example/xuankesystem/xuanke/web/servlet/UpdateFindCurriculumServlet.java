package com.example.xuankesystem.xuanke.web.servlet;

import com.example.xuankesystem.xuanke.domain.Curriculum;
import com.example.xuankesystem.xuanke.domain.User;
import com.example.xuankesystem.xuanke.service.CurriculumService;
import com.example.xuankesystem.xuanke.service.UserService;
import com.example.xuankesystem.xuanke.service.impl.CurriculumServiceImpl;
import com.example.xuankesystem.xuanke.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/updateFindCurriculumServlet")
public class UpdateFindCurriculumServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id=Integer.parseInt(request.getParameter("id"));
        CurriculumService service=new CurriculumServiceImpl();
        //将id放入HttpSession中
        HttpSession session=request.getSession();
        if(session.getAttribute("CURRICULUMUPDATEID_SERVE")!=null){
            session.removeAttribute("CURRICULUMUPDATEID_SERVE");
        }
        request.getSession().setAttribute("CURRICULUMUPDATEID_SERVE",id);
        //转发跳转到修改页面填写修改数据
        request.getRequestDispatcher("/curriculumupdate.html").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
