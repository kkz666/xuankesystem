package com.zhangkai.www.xuankesystem.dao;

import com.zhangkai.www.xuankesystem.domain.User;

import java.util.List;

public interface UserDaoMine {
    public User findByUsername(String username);
    public void save(User user);
    public User findByUsernameAndPassword(String username,String password);
    public List<User> findAll();
    //public void updatemessage(User user);
    public boolean delete(User user);
    public boolean update(User user,int id);
    public User findById(int id);
    public boolean findPower(int id);
    public boolean daoRu(List<User> list);
}
