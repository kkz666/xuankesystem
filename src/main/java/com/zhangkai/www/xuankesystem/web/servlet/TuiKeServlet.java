package com.zhangkai.www.xuankesystem.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhangkai.www.xuankesystem.domain.Curriculum;
import com.zhangkai.www.xuankesystem.domain.ResultInfo;
import com.zhangkai.www.xuankesystem.domain.User;
import com.zhangkai.www.xuankesystem.service.CurriculumService;
import com.zhangkai.www.xuankesystem.service.Stu_CurService;
import com.zhangkai.www.xuankesystem.service.UserService;
import com.zhangkai.www.xuankesystem.service.impl.CurriculumServiceImpl;
import com.zhangkai.www.xuankesystem.service.impl.Stu_CurServiceImpl;
import com.zhangkai.www.xuankesystem.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/tuiKeServlet")
public class TuiKeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("退课servlet执行了");
        int id=Integer.parseInt(request.getParameter("id"));
        System.out.println("得到的id="+id);
        Stu_CurService service=new Stu_CurServiceImpl();
        UserService userservice=new UserServiceImpl();
        CurriculumService curservice=new CurriculumServiceImpl();
        Curriculum c=curservice.findById(id);
        HttpSession session=request.getSession();
        User u=(User)session.getAttribute("user");
        System.out.println("登录的user为"+u);
        boolean flag=service.TuiKe(id,u.getId());
        ResultInfo info=new ResultInfo();
        if(flag==false){
            info.setFlag(false);
            info.setErrorMsg("删除失败");
            response.sendRedirect("TuiKe_fail.html");
            //request.getRequestDispatcher("/TuiKe_fail.html").forward(request,response);
        }else{
            info.setFlag(true);
            request.setCharacterEncoding("utf-8");
            response.sendRedirect("TuiKe_ok.html");
            //request.getRequestDispatcher("/TuiKe_ok.html").forward(request,response);
        }
        ObjectMapper mapper=new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),info);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    this.doGet(request, response);
    }
}
