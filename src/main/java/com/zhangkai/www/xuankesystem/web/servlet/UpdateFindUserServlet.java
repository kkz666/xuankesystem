package com.zhangkai.www.xuankesystem.web.servlet;

import com.zhangkai.www.xuankesystem.service.UserService;
import com.zhangkai.www.xuankesystem.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/updateFindUserServlet")
public class UpdateFindUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id=Integer.parseInt(request.getParameter("id"));
        UserService service=new UserServiceImpl();
        HttpSession session=request.getSession();
        if(session.getAttribute("UPDATEID_SERVE")!=null){
            session.removeAttribute("UPDATEID_SERVE");
        }
        //将id放入HttpSession中
        request.getSession().setAttribute("UPDATEID_SERVE",id);
        //转发跳转到修改页面填写修改数据
        request.getRequestDispatcher("/newupdate.html").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
