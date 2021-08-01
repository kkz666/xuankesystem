package com.example.xuankesystem.xuanke.service;

import com.example.xuankesystem.xuanke.domain.User;

import java.util.List;

public interface UserService {
    boolean regist(User user);
    User login(User user);
    public List<User> findAll();
    public boolean delete(User user);
    public User findByUsername(String username);
    public boolean update(User user,int id);
    public User findById(int id);
}

