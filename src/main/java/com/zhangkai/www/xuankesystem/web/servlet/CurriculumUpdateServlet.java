package com.zhangkai.www.xuankesystem.web.servlet;

import com.zhangkai.www.xuankesystem.domain.Curriculum;
import com.zhangkai.www.xuankesystem.domain.ResultInfo;
import com.zhangkai.www.xuankesystem.service.CurriculumService;
import com.zhangkai.www.xuankesystem.service.impl.CurriculumServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.Date;
import java.util.Map;

@WebServlet("/curriculumUpdateServlet")
public class CurriculumUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
