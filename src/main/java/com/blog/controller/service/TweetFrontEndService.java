package com.blog.controller.service;

import com.blog.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TweetFrontEndService {
    @Autowired
    TweetService tweetService;

}
