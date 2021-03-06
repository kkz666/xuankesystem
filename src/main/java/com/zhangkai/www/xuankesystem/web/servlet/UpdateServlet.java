package com.zhangkai.www.xuankesystem.web.servlet;

import com.zhangkai.www.xuankesystem.domain.ResultInfo;
import com.zhangkai.www.xuankesystem.domain.User;
import com.zhangkai.www.xuankesystem.service.UserService;
import com.zhangkai.www.xuankesystem.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/updateServlet")
public class UpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        int id=(Integer)session.getAttribute("UPDATEID_SERVE");
        User user =new User();
        Map<String,String []> map= request.getParameterMap();
        try{
            BeanUtils.populate(user,map);//赋值
        }catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        UserService service=new UserServiceImpl();
        boolean flag=service.update(user,id);
        ResultInfo info=new ResultInfo();
        if(flag==false){
            info.setFlag(false);
            info.setErrorMsg("修改失败");
        }else{
            info.setFlag(true);
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
