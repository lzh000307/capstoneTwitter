package com.blog.service.impl;

import com.blog.dao.UserDao;
import com.blog.pojo.User;
import com.blog.service.TweetService;
import com.blog.service.UserService;
import com.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private TweetService tweetService;

    @Override
    public User checkUser(String username, String password) {
        User user = userDao.queryByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }

    /**
     * 注册服务，前置条件：用户名和密码都不为空且用户名唯一
     * @param user
     */
    @Override
    public void signUp(User user) {
        //能到这里，说明用户名和密码都不为空且用户名唯一
        //初始nickname等同于username
        user.setNickname(user.getUsername());
        //初始头像为华南农业大学校徽（也有可能是二刺猿
        user.setAvatar("https://wkphoto.cdn.bcebos.com/a8773912b31bb051c3b2394d267adab44bede0c5.jpg");
        //加密密码
        user.setPassword(MD5Utils.code(user.getPassword()));
        //status=1,表示正常注册用户
        user.setStatus(1);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        userDao.addUser(user);
    }

    @Override
    public boolean existUsername(String username) {
        User user = userDao.queryByUsername(username);
        if (user != null) {
            return true; //用户名已存在
        }else
            return false; //用户名不存在
    }

    @Override
    public User findById(Long id) {
        User user = userDao.findById(id);
        //密码扔掉
        user.setPassword(null);
        return user;
    }

    @Override
    public List<User> getAllUser() {
        List<User> users = userDao.findAll();
        for(User user:users){
            user.setPassword(null);
        }
        return users;
    }

    @Override
    public void setStatus(Long id, Integer status) {
        //找到User
        User user = userDao.findById(id);
        Integer originStatus = user.getStatus();
        if(user!=null) {
            //设置status
            user.setStatus(status);
            //更新
            userDao.updateUser(user);
            //更新tweet表中的status
            tweetService.updateStatusByUserId(id, originStatus, status);
        }
    }
}
