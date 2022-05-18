package com.blog.service.impl;

import com.blog.dao.LikeDao;
import com.blog.pojo.Like;
import com.blog.pojo.Tweet;
import com.blog.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private LikeDao likeDao;


    @Override
    public void addLike(Long userId, Long tweetId) {
        likeDao.insert(userId, tweetId);
    }

    @Override
    public void deleteLike(Long userId, Long tweetId) {
        likeDao.delete(userId, tweetId);
    }

    @Override
    public boolean isLike(Long userId, Long tweetId) {
        Like like = likeDao.get(userId, tweetId);
        if(like == null) {
            return false;
        }
        return true;
    }
}
