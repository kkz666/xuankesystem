package com.zhangkai.www.xuankesystem.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhangkai.www.xuankesystem.domain.Curriculum;
import com.zhangkai.www.xuankesystem.domain.ResultInfo;
import com.zhangkai.www.xuankesystem.domain.User;
import com.zhangkai.www.xuankesystem.service.CurriculumService;
import com.zhangkai.www.xuankesystem.service.Stu_CurService;
import com.zhangkai.www.xuankesystem.service.impl.CurriculumServiceImpl;
import com.zhangkai.www.xuankesystem.service.impl.Stu_CurServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

@WebServlet("/selectCurriculumServlet")
public class SelectCurriculumServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GregorianCalendar calendar = new GregorianCalendar();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        System.out.println(hour);
        if(hour>23||hour<6){
            request.getRequestDispatcher("/selectcurriculum_fail_limittime.html").forward(request,response);
        }
        HttpSession session=request.getSession();
        //获取选课人
        User user=(User)session.getAttribute("user");
        //课程id
        int id=Integer.parseInt(request.getParameter("id"));
        Stu_CurService service=new Stu_CurServiceImpl();
        CurriculumService curservice=new CurriculumServiceImpl();
        Curriculum cur=curservice.findById(id);
        boolean flag=service.selectcurriculum(user,cur);
        ResultInfo info =new ResultInfo();
        if(flag){
            //选课成功
            info.setFlag(true);
            info.setErrorMsg("选课成功!");
            request.getRequestDispatcher("/selectcurriculum_ok.html").forward(request,response);
        }else{
            //选课失败
            info.setFlag(false);
            info.setErrorMsg("选课失败!");
            request.getRequestDispatcher("/selectcurriculum_fail_chongfu.html").forward(request,response);
        }
        //request.getRequestDispatcher("/selectcurriculum_ok.html").forward(request,response);
        //序列化为info
        ObjectMapper mapper=new ObjectMapper();
        String json=mapper.writeValueAsString(info);
        response.setContentType("application/json;charset=utf-8");
        //将json数据回写
        response.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    this.doGet(request,response);
    }
}
