package com.blog.service;

public interface UserCollectionService {


    public void addUserCollection(Long userId, Long tweetId);

    public void deleteUserCollection(Long userId, Long tweetId);

    public boolean isUserCollection(Long userId, Long tweetId);


}
