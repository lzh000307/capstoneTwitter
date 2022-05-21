package com.blog.service;

import com.blog.pojo.Censorship;
import com.blog.pojo.Tag;

import java.util.List;

public interface CensorshipService {

    int add(String words);

    Censorship getById(int id);

    List<Censorship> getAll();

    int update(Censorship censorship);

    int delete(int id);
}
