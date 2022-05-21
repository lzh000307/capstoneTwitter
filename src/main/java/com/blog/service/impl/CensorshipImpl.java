package com.blog.service.impl;

import com.blog.dao.CensorshipDao;
import com.blog.pojo.Censorship;
import com.blog.service.CensorshipService;
import com.blog.service.CommentService;
import com.blog.service.TweetService;
import com.google.j2objc.annotations.AutoreleasePool;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CensorshipImpl implements CensorshipService {
    @Autowired
    private CensorshipDao censorshipDao;
    @Autowired
    private TweetService tweetService;
    @Autowired
    private CommentService commentService;

    @Override
    public int add(String words) {
        //查找关键词是否存在
        Censorship censorship = censorshipDao.getCensorshipFromWord(words);
        if(censorship != null){
            return 0;
        }
        Censorship censorship1 = new Censorship();
        censorship1.setWord(words);
        censorshipDao.insert(censorship1);
        //推送给TweetService和CommentService屏蔽
        tweetService.censorshipAPI(censorship1);
        commentService.censorshipAPI(censorship1);
        return 1;
    }

    @Override
    public int add(Censorship censorship) {
        return add(censorship.getWord());
    }

    @Override
    public Censorship getById(int id) {
        return censorshipDao.getCensorshipFromId(id);
    }

    @Override
    public List<Censorship> getAll() {
        return censorshipDao.queryAll();
    }

    @Override
    public int update(Censorship censorship) {
        int id = censorship.getId();
        //检查一下有没有这个关键词
        Censorship temp = censorshipDao.getCensorshipFromId(id);
        if(temp == null){
            return 0;
        }
        //如果为空，异常处理
        if(censorship.getWord()==null)
            return 0;
        //解除屏蔽
        //必须先update，再remove，否则逻辑有错误
        censorshipDao.update(censorship);
        tweetService.removeCensorship(id);
        commentService.removeCensorship(id);
        //推送给TweetService和CommentService屏蔽
        tweetService.censorshipAPI(censorship);
        commentService.censorshipAPI(censorship);
        return 0;
    }

    @Override
    public int delete(int id) {
        //检查一下有没有这个关键词
        Censorship censorship = censorshipDao.getCensorshipFromId(id);
        if(censorship == null){
            return 0;
        }
        //解除屏蔽
        //必须先update，再remove，否则逻辑有错误
        censorshipDao.deleteById(id);
        tweetService.removeCensorship(id);
        commentService.removeCensorship(id);
        return 0;
    }

    @Override
    public Censorship getByWord(String word) {
        return censorshipDao.getCensorshipFromWord(word);
    }

    /**
     * 检查一下是否有敏感词，反向检测，遍历所有的敏感词，如果有一个匹配上，就返回true
     * @param content
     * @return
     */
    @Override
    public int censor(String content) {
        List<Censorship> list = censorshipDao.queryAll();
        for (Censorship censorship : list) {
            if(content.contains(censorship.getWord())){
                return censorship.getId();
            }
        }
        return -1;
    }
}
