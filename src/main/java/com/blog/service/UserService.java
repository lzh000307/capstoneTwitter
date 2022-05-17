package com.blog.service;

import com.blog.pojo.User;

public interface UserService {

    public User checkUser(String username, String password);

    void signUp(User user);

    boolean existUsername(String username);
}
