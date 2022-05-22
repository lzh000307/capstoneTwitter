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
        model.addAttribute("tags", tagService.getAllTag());
    }
    @GetMapping("/user/collections")  //显示自己的收藏列表
    public String collectionsManagement(/*@RequestParam(required = false,defaultValue = "1",value = "pagenum")int pagenum, */Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
//        PageHelper.startPage(pagenum, 8);
        //通过收藏列表得到推文列表
        List<Tweet> collectTweet = userCollectionService.getUserCollectionTweets(user.getId());
        List<TweetFrontEnd> tweetfes = tweetFrontEndConvector.convertToTweetFrontEnd(collectTweet);
        PageInfo pageInfo = new PageInfo(tweetfes);
        model.addAttribute("pageInfo", pageInfo);
        setTypeAndTag(model);  //查询类型和标签
        return "usercollections";
    }

    /**
     * 土方法，我知道这个方法有重复，为了跳转...有时间再设置get跳转
     * @param id
     * @param session
     * @return
     */
    @GetMapping("/user/collections/delete/{id}")
    public String deleteCollection(@PathVariable Long id, HttpSession session){
        User user = (User) session.getAttribute("user");
        //如果已经收藏，则取消收藏
        if(userCollectionService.isUserCollection(user.getId(), id)){
            userCollectionService.deleteUserCollection(user.getId(), id);
        }
        return "redirect:/user/collections";
    }


}
