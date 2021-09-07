package com.zhangkai.www.xuankesystem.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhangkai.www.xuankesystem.domain.Curriculum;
import com.zhangkai.www.xuankesystem.domain.ResultInfo;
import com.zhangkai.www.xuankesystem.service.CurriculumService;
import com.zhangkai.www.xuankesystem.service.impl.CurriculumServiceImpl;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@WebServlet("/curriculum/*")
public class JiChengCurriculumServlet extends BaseServlet {
    public void curriculumcategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CurriculumService service=new CurriculumServiceImpl();
        List<Curriculum> cs= service.findAll();
        ObjectMapper mapper =new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),cs);
    }
    public void curriculumdelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id=Integer.parseInt(request.getParameter("id"));
        CurriculumService service=new CurriculumServiceImpl();
        Curriculum c=service.findById(id);
        boolean flag=service.delete(c);
        ResultInfo info=new ResultInfo();
        if(flag==false){
            info.setFlag(false);
            info.setErrorMsg("删除失败");
        }else{
            info.setFlag(true);
            request.setCharacterEncoding("utf-8");
            request.getRequestDispatcher("/curriculumdelete_ok.html").forward(request,response);
        }
        ObjectMapper mapper=new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),info);
    }
    public void curriculumecho(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        int id=(Integer)session.getAttribute("CURRICULUMUPDATEID_SERVE");
        Curriculum curriculum =null;
        CurriculumService service=new CurriculumServiceImpl();
        curriculum=service.findById(id);
        //获取原来的数据，用于数据回写
        List<Curriculum> cs=new ArrayList<Curriculum>();
        cs.add(curriculum);
        //List<User> cs= service.findAll();
        ObjectMapper mapper =new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),cs);
    }
    public void curriculumregist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.获取数据使用beanutils
        Map<String,String []> map= request.getParameterMap();
        //2.封装对象
        Curriculum curriculum =new Curriculum();
        try{
            ConvertUtils.register(new Converter() {
                @Override
                public Object convert(Class aClass, Object o) {
                    // 如果字符串为null，则返回null
                    if(o == null)
                        return null;

                    // 如果字符串为空格，则返回null
                    String str = (String) o;
                    if(str.trim().equals(""))
                        return null;

                    // 将字符串转为Date类型
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        return formatter.parse(o.toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return new Date();
                }
            }, Date.class);
            BeanUtils.populate(curriculum,map);//赋值
        }catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        //3.调用service完成注册
        CurriculumService service =new CurriculumServiceImpl();
        boolean flag=service.regist(curriculum);
        ResultInfo info =new ResultInfo();
        //4.返回响应结果
        if(flag){
            //注册成功
            info.setFlag(true);
        }else{
            //注册失败
            info.setFlag(false);
            info.setErrorMsg("注册失败!");
        }
        //序列化为info
        ObjectMapper mapper=new ObjectMapper();
        String json=mapper.writeValueAsString(info);
        response.setContentType("application/json;charset=utf-8");
        //将json数据回写
        response.getWriter().write(json);
    }
    public void curriculmupdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        int id=(Integer)session.getAttribute("CURRICULUMUPDATEID_SERVE");
        Curriculum curriculum =new Curriculum();
        Map<String,String []> map= request.getParameterMap();
        try{
            ConvertUtils.register(new Converter() {
                @Override
                public Object convert(Class aClass, Object o) {
                    // 如果字符串为null，则返回null
                    if(o == null)
                        return null;

                    // 如果字符串为空格，则返回null
                    String str = (String) o;
                    if(str.trim().equals(""))
                        return null;

                    // 将字符串转为Date类型
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        return formatter.parse(o.toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return new Date();
                }
            }, Date.class);
            BeanUtils.populate(curriculum,map);//赋值
        }catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        CurriculumService service=new CurriculumServiceImpl();
        boolean flag=service.update(curriculum,id);
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
    public void updatefindcurriculum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
}
