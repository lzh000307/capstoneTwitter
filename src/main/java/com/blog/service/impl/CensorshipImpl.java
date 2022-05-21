package com.blog.service.impl;

import com.blog.dao.CensorshipDao;
import com.blog.pojo.Censorship;
import com.blog.service.CensorshipService;
import com.google.j2objc.annotations.AutoreleasePool;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CensorshipImpl implements CensorshipService {
    @Autowired
    private CensorshipDao censorshipDao;


    @Override
    public int add(String words) {
        //查找关键词是否存在
        Censorship censorship = censorshipDao.getCensorshipFromWord(words);
        if(censorship != null){
            return 0;
        }
        return censorshipDao.addCensorship(words);
    }

    @Override
    public Censorship getById(int id) {
        return censorshipDao.getCensorshipFromId(id);
    }

    @Override
    public List<Censorship> getAll() {
        return censorshipDao.getAllCensorship();
    }

    @Override
    public int update(Censorship censorship) {
        return censorshipDao.updateCensorship(censorship.getId(), censorship.getWord());
    }

    @Override
    public int delete(int id) {
        return censorshipDao.deleteCensorshipById(id);
    }
}
