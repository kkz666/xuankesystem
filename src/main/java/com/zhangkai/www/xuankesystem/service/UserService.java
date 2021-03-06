package com.zhangkai.www.xuankesystem.service;

import com.zhangkai.www.xuankesystem.domain.User;

import java.util.List;

public interface UserService {
    boolean regist(User user);
    User login(User user);
    public List<User> findAll();
    public boolean delete(User user);
    public User findByUsername(String username);
    public boolean update(User user,int id);
    public User findById(int id);
    public boolean findPower(int id);
    public boolean daoRu(List<User>list);
}

