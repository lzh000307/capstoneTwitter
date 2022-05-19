package com.blog.service;

import com.blog.pojo.UserCollection;

import java.util.List;

public interface UserCollectionService {


    public void addUserCollection(Long userId, Long tweetId);

    public void deleteUserCollection(Long userId, Long tweetId);

    public boolean isUserCollection(Long userId, Long tweetId);

    List<UserCollection> getUserCollections(Long id);
}
