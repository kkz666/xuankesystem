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

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    private UserService service = new UserServiceImpl();

    /**
     * 注册功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String check=request.getParameter("check");
        //从session中获取验证码
        HttpSession session=request.getSession();
        String checkcode_server=(String)session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");//验证码使用一次
        if(checkcode_server==null||!checkcode_server.equalsIgnoreCase(check)){
            ResultInfo info=new ResultInfo();
            info.setFlag(false);
            info.setErrorMsg("验证码错误！");
            //序列化为info
            ObjectMapper mapper=new ObjectMapper();
            String json=mapper.writeValueAsString(info);
            response.setContentType("application/json;charset=utf-8");
            //将json数据回写
            response.getWriter().write(json);
            return ;
        }

        //1.获取数据使用beanutils
        Map<String,String []> map= request.getParameterMap();
        //2.封装对象
        User user =new User();
        try{
            BeanUtils.populate(user,map);
        }catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        //3.调用service完成注册
        //UserService service =new UserServiceImpl();抽取出来
        boolean flag=service.regist(user);
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

    /**
     * 登录功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //获取用户名密码数据
        //验证码校验
        String check=request.getParameter("check");
        //从session中获取验证码
        HttpSession session=request.getSession();
        String checkcode_server=(String)session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");//验证码使用一次
        if(checkcode_server==null||!checkcode_server.equalsIgnoreCase(check)){
            ResultInfo info=new ResultInfo();
            info.setFlag(false);
            info.setErrorMsg("验证码错误！");
            //序列化为info
            ObjectMapper mapper=new ObjectMapper();
            String json=mapper.writeValueAsString(info);
            response.setContentType("application/json;charset=utf-8");
            //将json数据回写
            response.getWriter().write(json);
            return ;
        }
        Map<String, String[]> map = request.getParameterMap();
        //封装对象
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        }catch(IllegalAccessException e) {
            e.printStackTrace();
        }catch(InvocationTargetException e){
            e.printStackTrace();
        }
        //查询
        //UserService service=new UserServiceImpl();抽取出来了
        User u=service.login(user);
        //判断
        ResultInfo info=new ResultInfo();
        if(u==null){
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误");
        }
        //判断登录成功
        if(u!=null){
            request.getSession().setAttribute("user",u);//登录成功标记
            info.setFlag(true);
        }
        //响应数据
        ObjectMapper mapper=new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),info);
    }

    /**
     * 查询单个对象
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Object user=request.getSession().getAttribute("user");
        /*ObjectMapper mapper=new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),user);*/
        writeValue(user,response);
    }

    /**
     * 退出功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath()+"/login.html");
    }
}
