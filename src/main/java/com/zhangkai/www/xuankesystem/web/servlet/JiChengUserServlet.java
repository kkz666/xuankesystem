package com.zhangkai.www.xuankesystem.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhangkai.www.xuankesystem.domain.ResultInfo;
import com.zhangkai.www.xuankesystem.domain.User;
import com.zhangkai.www.xuankesystem.service.CreateExcel;
import com.zhangkai.www.xuankesystem.service.UserService;
import com.zhangkai.www.xuankesystem.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/user/*")
public class JiChengUserServlet extends BaseServlet {
    public void category(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService service=new UserServiceImpl();
        List<User> cs= service.findAll();
        ObjectMapper mapper =new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),cs);
    }
    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        UserService service=new UserServiceImpl();
        //将id放入HttpSession中
        request.getSession().setAttribute("DELETEID_SERVE",id);
        //转发跳转到修改页面填写修改数据
        request.getRequestDispatcher("/suredelete.html").forward(request,response);
    }
    public void echo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        int id=(Integer)session.getAttribute("UPDATEID_SERVE");
        System.out.println(id);
        User user =null;
        UserService service=new UserServiceImpl();
        user=service.findById(id);
        //获取原来的数据，用于数据回写
        List<User> cs=new ArrayList<User>();
        cs.add(user);
        //List<User> cs= service.findAll();
        ObjectMapper mapper =new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),cs);
    }
    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath()+"/newlogin.html");
    }
    public void findall(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService service=new UserServiceImpl();
        List<User> users=service.findAll();
        request.setAttribute("users",users);
    }
    public void finduser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object user=request.getSession().getAttribute("user");
        ObjectMapper mapper=new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),user);
    }
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        //System.out.println(user.getAdmin());
        //查询
        UserService service=new UserServiceImpl();
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
            if(service.findPower(u.getId())==true)info.setAdmin(true);
            info.setFlag(true);
        }
        //响应数据
        ObjectMapper mapper=new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),info);
    }
    public void registuser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        //1.获取数据使用beanutils
        Map<String,String []> map= request.getParameterMap();
        //2.封装对象
        User user =new User();
        try{
            BeanUtils.populate(user,map);//赋值
        }catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        //3.调用service完成注册
        UserService service =new UserServiceImpl();
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
    public void suredelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            request.getRequestDispatcher("/delete_ok.html").forward(request,response);
        }
        ObjectMapper mapper=new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),info);
    }
    public void updatefinduser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        int id=(Integer)session.getAttribute("UPDATEID_SERVE");
        User user =new User();
        Map<String,String []> map= request.getParameterMap();
        try{
            BeanUtils.populate(user,map);//赋值
        } catch (IllegalAccessException e)
         {
            e.printStackTrace();
        } catch (InvocationTargetException e){
            e.printStackTrace();
        }
        UserService service=new UserServiceImpl();
        boolean flag=service.update(user,id);
        ResultInfo info=new ResultInfo();
        if(!flag){
            info.setFlag(false);
            info.setErrorMsg("修改失败");
        }else{
            info.setFlag(true);
        }
        ObjectMapper mapper=new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),info);
    }
    public void userdaochu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService service=new UserServiceImpl();
        try{
            CreateExcel.createExcelByBeans(service.findAll());
            System.out.println("导出用户列表已执行");
        }catch(Exception e){
            e.printStackTrace();
        }

        request.getRequestDispatcher("/listshow1.html").forward(request,response);
    }
    public void userdaoru(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService service=new UserServiceImpl();
        boolean flag=false;
        System.out.println("前戏");
        try{
            List<User> list=CreateExcel.readExcel("/xuankesystem/src/main/resources/userReadExcel.xlsx");
            flag=service.daoRu(list);
            System.out.println("导入用户列表已执行");
        }catch(Exception e){
            e.printStackTrace();
        }
        if(flag==false){
            System.out.println("导入失败");
        }else{
            System.out.println("导入成功");
            request.getRequestDispatcher("/listshow1.html").forward(request,response);
        }
    }

}
