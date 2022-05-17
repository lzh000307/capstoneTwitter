package com.blog.service;

import com.blog.pojo.Tag;
import com.blog.pojo.Topic;

import java.util.List;

public interface TopicService {

    int saveTopic(Topic topic);

    Topic getTopic(Long id);

    Topic getTopicByName(String name);

    List<Topic> getAllTopic();

//    List<Topic> getBlogTopic();  //首页展示博客标签

//    List<Topic> getTopicByString(String text);   //从字符串中获取tag集合

    int updateTopic(Topic topic);

    int deleteTopic(Long id);
}
