package com.example.xuankesystem.xuanke.service.impl;

import com.example.xuankesystem.xuanke.dao.UserDao;
import com.example.xuankesystem.xuanke.dao.impl.UserDaoImpl;
import com.example.xuankesystem.xuanke.domain.User;
import com.example.xuankesystem.xuanke.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao=new UserDaoImpl();

    public boolean regist(User user){
        //1.根据用户名查询用户对象
    User u=userDao.findByUsername(user.getUsername());
    //2.保存用户信息
        if(u!=null){//用户名已存在注册失败
            return false;
        }
    userDao.save(user);
        return true;
    }

    /**
     * 登录方法
     * @param user
     * @return
     */
    public User login(User user){
        return userDao.findByUsernameAndPassword(user.getUsername(),user.getPassword());
    }
    public List<User> findAll(){
        return userDao.findAll();
    }
    public boolean delete(User user){
        if(user==null){
            return false;
        }else {
            userDao.delete(user);
            return true;
        }
    }
    public User findByUsername(String username){
        User u=userDao.findByUsername(username);
        System.out.println(u.getUsername());
        return u;
    }
    public boolean update(User user){
        if(user==null){
            return false;
        }else{
            userDao.update(user);
            return true;
        }
    }
    public int findId(User user){
        if(user==null){
            return -1;
        }else{
            return userDao.findId(user);
        }
    }
}
