package com.example.xuankesystem.xuanke.dao;

import com.example.xuankesystem.xuanke.domain.User;

import java.util.List;

public interface UserDao {
    /**
     *根据用户名查询
     * @param username
     * @return
     */
    public User findByUsername(String username);

    /**
     * 用户保存
     * @param user
     */
    public void save(User user);
    public User findByUsernameAndPassword(String username,String password);
    public List<User>findAll();
    //public void updatemessage(User user);
    public boolean delete(User user);
    public boolean update(User user);
    public int findId(User user);
}
