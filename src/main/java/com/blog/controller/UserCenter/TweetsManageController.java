package com.blog.controller.UserCenter;

import com.blog.pojo.Blog;
import com.blog.pojo.Tweet;
import com.blog.pojo.User;
import com.blog.pojo.UserCollection;
import com.blog.service.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/usercenter")
public class TweetsManageController {

    @Autowired
    private TweetService tweetService;
    @Autowired
    private TagService tagService;
    @Autowired
    private UserCollectionService userCollectionService;
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

    @GetMapping("/collections")  //显示自己的收藏列表
    public String collectionsManagement(@RequestParam(required = false,defaultValue = "1",value = "pagenum")int pagenum, Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            return "redirect:/login";
        }
        PageHelper.startPage(pagenum, 10);
        //通过收藏列表得到推文列表
        List<Tweet> collectTweet = userCollectionService.getUserCollectionTweets(user.getId());
        //得到分页结果对象
        PageInfo pageInfo = new PageInfo(collectTweet);
        model.addAttribute("pageInfo", pageInfo);
        setTypeAndTag(model);  //查询类型和标签
        return "usercenter/collections";
    }

    /**
     * 土方法，我知道这个方法有重复，为了跳转...有时间再设置get跳转
     * @param id
     * @param session
     * @return
     */
    @GetMapping("/deleteCollection/{id}")
    public String deleteCollection(@PathVariable Long id, HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        Tweet tweet = tweetService.getTweet(id);
        //如果已经收藏，则取消收藏
        if(userCollectionService.isUserCollection(user.getId(), id)){
            userCollectionService.deleteUserCollection(user.getId(), id);
        }
        return "redirect:/usercenter/collections";
    }
}
