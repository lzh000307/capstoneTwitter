package com.blog.controller;

import com.blog.pojo.Blog;
import com.blog.pojo.Tag;
import com.blog.pojo.Tweet;
import com.blog.service.BlogService;
import com.blog.service.TagService;
import com.blog.service.TrendService;
import com.blog.service.TweetService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TagShowController {

    @Autowired
    private TagService tagService;
    @Autowired
    private TrendService trendService;
//    @Autowired
//    private BlogService blogService;
    @Autowired
    private TweetService tweetService;

    @GetMapping("/tags/{id}")
    public String types(@PathVariable Long id, @RequestParam(required = false,defaultValue = "1",value = "pagenum")int pagenum,
                        Model model){
        PageHelper.startPage(pagenum, 100);  //开启分页
        List<Tag> tags = tagService.getAllTag(); //获取所有标签
        //-1从导航点过来的
        if (id == -1){
            id = tags.get(0).getId();
        }
//        List<Blog> blogs = blogService.getByTagId(id);
        List<Long> trends = trendService.getTweetIdByTagId(id);
        List<Tweet> tweets = new ArrayList<>();
        for(Long trend : trends){
            tweets.add(tweetService.getTweet(trend));
        }
        for (Tweet tweet : tweets) {
            System.out.println(tweets);
        }
        PageInfo<Tweet> pageInfo = new PageInfo<>(tweets);
        model.addAttribute("tags", tags);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("activeTagId", id);

        return "tags";
    }
}
