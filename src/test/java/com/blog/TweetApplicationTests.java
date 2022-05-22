package com.blog;

import com.blog.dao.TweetDao;
import com.blog.pojo.Tweet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TweetApplicationTests {
    @Autowired
    TweetDao tweetDao;
    //cha


    @Test
    public void test(){
        Tweet tweet = tweetDao.queryById(1L);
        System.out.println(tweet);
        tweet.setStatus(2);
        tweetDao.insert(tweet);
        System.out.println(tweetDao.queryById(1L));
        tweet.setId(2L);
        tweetDao.update(tweet);
        System.out.println(tweetDao.queryById(2L));


    }

}

