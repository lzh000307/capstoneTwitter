package com.blog.controller;

import com.blog.controller.entity.TweetFrontEnd;
import com.blog.pojo.Tag;
import com.blog.pojo.Tweet;
import com.blog.service.*;
import com.blog.util.TweetFrontEndConvector;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private TweetService tweetService;
    @Autowired
    private TrendService trendService;
    @Autowired
    private TagService tagService;
    @Autowired
    private TweetFrontEndConvector tweetFrontEndConvector;

    @GetMapping("/")
    public String toIndex(/*@RequestParam(required = false,defaultValue = "1",value = "pagenum")int pagenum, */Model model){
        //巨坑
//        PageHelper.startPage(pagenum, 10);
        List<Tweet> indexTweet = tweetService.getIndex();
        List<TweetFrontEnd> tweetfes = tweetFrontEndConvector.convertToTweetFrontEnd(indexTweet);
        PageInfo pageInfo = new PageInfo(tweetfes);
        List<Tag> allTag = tagService.getAllTag();  //获取趋势
        //点赞排行榜
        List<Tweet> allIndex = tweetService.getIndex();
        List<Tweet> likeTweets = tweetService.sortByLike(allIndex);
        List<TweetFrontEnd> likeTweetfes = tweetFrontEndConvector.convertToTweetFrontEnd(likeTweets);
        //得到分页结果对象
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("tags", allTag);
        model.addAttribute("likeTweetfes", likeTweetfes);
        return "index";
    }

    @PostMapping("/search")
    public String search(@RequestParam(required = false,defaultValue = "1",value = "pagenum")int pagenum,
                         @RequestParam String query, Model model){

//        PageHelper.startPage(pagenum, 8);
        Tweet temp = new Tweet();
        temp.setContent(query);
        temp.setTitle(query);
        List<Tweet> searchTweet = tweetService.searchAllTweet(temp);
        List<TweetFrontEnd> tweetfes = tweetFrontEndConvector.convertToTweetFrontEnd(searchTweet);
        PageInfo pageInfo = new PageInfo(tweetfes);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("query", query);
        return "search";
    }
}
