package com.zhangkai.www.xuankesystem.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhangkai.www.xuankesystem.domain.Curriculum;
import com.zhangkai.www.xuankesystem.domain.ResultInfo;
import com.zhangkai.www.xuankesystem.service.CurriculumService;
import com.zhangkai.www.xuankesystem.service.Stu_CurService;
import com.zhangkai.www.xuankesystem.service.impl.CurriculumServiceImpl;
import com.zhangkai.www.xuankesystem.service.impl.Stu_CurServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/tuiKeServlet")
public class TuiKeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id=Integer.parseInt(request.getParameter("id"));
        Stu_CurService service=new Stu_CurServiceImpl();
        //CurriculumService curservice=new CurriculumServiceImpl();
        //Curriculum c=curservice.findById(id);
        boolean flag=service.TuiKe(id);
        ResultInfo info=new ResultInfo();
        if(flag==false){
            info.setFlag(false);
            info.setErrorMsg("删除失败");
        }else{
            info.setFlag(true);
            request.setCharacterEncoding("utf-8");
            request.getRequestDispatcher("/TuiKe_ok.html").forward(request,response);
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
