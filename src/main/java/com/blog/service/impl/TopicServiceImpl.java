package com.blog.service.impl;

import com.blog.dao.TagDao;
import com.blog.dao.TopicDao;
import com.blog.pojo.Tag;
import com.blog.pojo.Topic;
import com.blog.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    TagDao tagDao;
    @Autowired
    TopicDao topicDao;
    @Override
    public int saveTopic(Topic topic) {
        return topicDao.insert(topic);
    }

    @Override
    public Topic getTopic(Long id) {
        return topicDao.queryById(id);
    }

    @Override
    public Topic getTopicByName(String name) {
        return topicDao.queryByName(name);
    }

    @Override
    public List<Topic> getAllTopic() {
        return topicDao.queryAll();
    }

    @Override
    public int updateTopic(Topic topic) {
        return topicDao.update(topic);
    }

    @Override
    public int deleteTopic(Long id) {
        return topicDao.deleteById(id);
    }
}
