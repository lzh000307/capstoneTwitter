package com.blog.controller;

import com.blog.controller.entity.TweetFrontEnd;
import com.blog.pojo.Tweet;
import com.blog.pojo.User;
import com.blog.pojo.UserCollection;
import com.blog.service.TagService;
import com.blog.service.TweetService;
import com.blog.service.UserCollectionService;
import com.blog.util.TweetFrontEndConvector;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserCollectionController {

    @Autowired
    private TweetService tweetService;
    @Autowired
    private TagService tagService;
    @Autowired
    private UserCollectionService userCollectionService;

    @Autowired
    private TweetFrontEndConvector tweetFrontEndConvector;

    public void setTypeAndTag(Model model) {
//        model.addAttribute("types", typeService.getAllType());
        model.addAttribute("tags", tagService.getAllTag());
    }

    @GetMapping("/tweets")  //显示自己的推文列表
    public String tweetsManagement(@RequestParam(required = false,defaultValue = "1",value = "pagenum")int pagenum, Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            return "redirect:/login";
        }
        PageHelper.startPage(pagenum, 8);
        List<Tweet> allTweet = tweetService.getTweetsByUserId(user.getId());
        //得到分页结果对象
        PageInfo pageInfo = new PageInfo(allTweet);
        model.addAttribute("pageInfo", pageInfo);
        setTypeAndTag(model);  //查询类型和标签
        return "usercenter/tweets";
    }

    @GetMapping("/user/collections")  //显示自己的收藏列表
    public String collectionsManagement(@RequestParam(required = false,defaultValue = "1",value = "pagenum")int pagenum, Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            return "redirect:/login";
        }
        PageHelper.startPage(pagenum, 8);
        //取得收藏列表
        List<UserCollection> userCollections = userCollectionService.getUserCollections(user.getId());
        //通过收藏列表得到推文列表
        List<Tweet> collectTweet = new ArrayList<>();
        for(UserCollection userCollection : userCollections){
            collectTweet.add(tweetService.getTweet(userCollection.getTweetId()));
        }
        //得到分页结果对象
        List<TweetFrontEnd> tweetfes = tweetFrontEndConvector.convertToTweetFrontEnd(collectTweet);
        PageInfo pageInfo = new PageInfo(tweetfes);
        model.addAttribute("pageInfo", pageInfo);
        setTypeAndTag(model);  //查询类型和标签
        return "usercollections";
    }


}
