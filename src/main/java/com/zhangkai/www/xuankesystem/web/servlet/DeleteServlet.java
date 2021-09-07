package com.zhangkai.www.xuankesystem.web.servlet;

import com.zhangkai.www.xuankesystem.domain.ResultInfo;
import com.zhangkai.www.xuankesystem.domain.User;
import com.zhangkai.www.xuankesystem.service.UserService;
import com.zhangkai.www.xuankesystem.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/deleteServlet")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        UserService service=new UserServiceImpl();
        //将id放入HttpSession中


        User u=service.findById(id);
        boolean flag=service.delete(u);
        ResultInfo info=new ResultInfo();
        if(flag==false){
            info.setFlag(false);
            info.setErrorMsg("删除失败");
            request.getRequestDispatcher("/delete_fail.html").forward(request,response);
        }else{
            info.setFlag(true);
            request.getRequestDispatcher("/delete_ok.html").forward(request,response);
        }
        //*request.getSession().setAttribute("DELETEID_SERVE",id);
        //转发跳转到修改页面填写修改数据
        //*request.getRequestDispatcher("/suredelete.html").forward(request,response);
        //response.sendRedirect("sureDeleteServlet");
        //request.getRequestDispatcher("/sureDeleteServlet").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    this.doGet(request,response);
    }
}
