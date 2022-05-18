package com.blog.service;

import com.blog.pojo.Tag;

import java.util.List;

public interface LikeService {


    public void addLike(Long userId, Long tweetId);

    public void deleteLike(Long userId, Long tweetId);

    public boolean isLike(Long userId, Long tweetId);


}
