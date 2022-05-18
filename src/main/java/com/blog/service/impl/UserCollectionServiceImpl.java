package com.blog.service.impl;
import com.blog.dao.UserCollectionDao;
import com.blog.pojo.UserCollection;
import com.blog.service.UserCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCollectionServiceImpl implements UserCollectionService {

    @Autowired
    private UserCollectionDao likeDao;


    @Override
    public void addUserCollection(Long userId, Long tweetId) {
        likeDao.insert(userId, tweetId);
    }

    @Override
    public void deleteUserCollection(Long userId, Long tweetId) {
        likeDao.delete(userId, tweetId);
    }

    @Override
    public boolean isUserCollection(Long userId, Long tweetId) {
        UserCollection like = likeDao.get(userId, tweetId);
        if(like == null) {
            return false;
        }
        return true;
    }
}
