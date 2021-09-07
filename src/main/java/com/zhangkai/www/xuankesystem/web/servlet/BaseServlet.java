package com.zhangkai.www.xuankesystem.web.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    @Override
protected void service(HttpServletRequest request, HttpServletResponse response)throws ServletException{
    String uri=request.getRequestURI();
    String methodName=uri.substring(uri.lastIndexOf('/')+1);
    try {
        Method method=this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
        method.invoke(this,request,response);
    }catch(NoSuchMethodException e){
        e.printStackTrace();
    }catch(InvocationTargetException e){
        e.printStackTrace();
    }catch(IllegalAccessException e){
        e.printStackTrace();
    }
    }
    public void writeValue(Object obj,HttpServletResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),obj);
    }
    public String writeValueAsString(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }
}
