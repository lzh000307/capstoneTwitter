package com.blog.service.impl;
import com.blog.dao.UserCollectionDao;
import com.blog.pojo.UserCollection;
import com.blog.service.UserCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCollectionServiceImpl implements UserCollectionService {

    @Autowired
    private UserCollectionDao userCollectionDao;


    @Override
    public void addUserCollection(Long userId, Long tweetId) {
        userCollectionDao.insert(userId, tweetId);
    }

    @Override
    public void deleteUserCollection(Long userId, Long tweetId) {
        userCollectionDao.delete(userId, tweetId);
    }

    @Override
    public boolean isUserCollection(Long userId, Long tweetId) {
        UserCollection like = userCollectionDao.get(userId, tweetId);
        if(like == null) {
            return false;
        }
        return true;
    }

    @Override
    public List<UserCollection> getUserCollections(Long id) {
        return userCollectionDao.getByUserId(id);
    }
}
