package com.blog.controller;

import com.blog.controller.entity.TweetFrontEnd;
import com.blog.pojo.Blog;
import com.blog.pojo.Tag;
import com.blog.pojo.Tweet;
import com.blog.pojo.Type;
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

//    @Autowired
//    private BlogService blogService;
    @Autowired
    private TweetService tweetService;
    @Autowired
    private TrendService trendService;

    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;
    @Autowired
    private TweetFrontEndConvector tweetFrontEndConvector;

    @GetMapping("/")
    public String toIndex(@RequestParam(required = false,defaultValue = "1",value = "pagenum")int pagenum, Model model){

        PageHelper.startPage(pagenum, 8);
        List<Tweet> indexTweet = tweetService.getIndex();
//        List<Type> allType = typeService.getBlogType();  //获取博客的类型(联表查询)
        List<Tag> allTag = tagService.getAllTag();  //获取博客的标签(联表查询)
//        List<Blog> recommendBlog =blogService.getAllRecommendBlog();  //获取推荐博客
        //TODO: 点赞

        System.out.println(indexTweet);
        //得到分页结果对象
        PageInfo pageInfo = new PageInfo(indexTweet);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("tags", allTag);
//        model.addAttribute("types", allType);
//        model.addAttribute("recommendBlogs", recommendBlog);
        return "index";
    }

    @PostMapping("/search")
    public String search(@RequestParam(required = false,defaultValue = "1",value = "pagenum")int pagenum,
                         @RequestParam String query, Model model){

        PageHelper.startPage(pagenum, 5);
//        List<Blog> searchBlog = blogService.getSearchBlog(query);
        Tweet temp = new Tweet();
        temp.setContent(query);
        temp.setTitle(query);
        List<Tweet> searchTweet = tweetService.searchAllTweet(temp);
        List<TweetFrontEnd> tweets = new ArrayList<>();
        for(Tweet tweet:searchTweet){
            tweets.add(tweetFrontEndConvector.convertToTweetFrontEnd(tweet));
        }
        PageInfo pageInfo = new PageInfo(tweets);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("query", query);
        return "search";
    }

    @GetMapping("/tweet/{id}")
    public String surfTweet(@PathVariable Long id, Model model){
//        Blog blog = blogService.getDetailedBlog(id);
        Tweet tweet = tweetService.getTweet(id);
        TweetFrontEnd tweetFE = tweetFrontEndConvector.convertToTweetFrontEnd(tweet);
        model.addAttribute("tweetfe", tweetFE);
        return "tweet";
    }
}
