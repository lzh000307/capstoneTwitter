package com.blog.service;

import com.blog.pojo.User;

import java.util.List;


public interface UserService {

    public User checkUser(String username, String password);

    void signUp(User user);

    boolean existUsername(String username);

    User findById(Long id);

    List<User> getAllUser();

    void setStatus(Long id, Integer status);

    void update(User user1);
}
