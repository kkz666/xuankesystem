package com.example.xuankesystem.xuanke.web.servlet;

import com.example.xuankesystem.xuanke.domain.ResultInfo;
import com.example.xuankesystem.xuanke.domain.User;
import com.example.xuankesystem.xuanke.service.UserService;
import com.example.xuankesystem.xuanke.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/sureDeleteServlet")
public class SureDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        int id=(Integer)session.getAttribute("DELETEID_SERVE");
        UserService service=new UserServiceImpl();
        User u=service.findById(id);
        boolean flag=service.delete(u);
        ResultInfo info=new ResultInfo();
        if(flag==false){
            info.setFlag(false);
            info.setErrorMsg("删除失败");
        }else{
            info.setFlag(true);
            //request.getRequestDispatcher("/delete_ok.html").forward(request,response);
        }
        ObjectMapper mapper=new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),info);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
