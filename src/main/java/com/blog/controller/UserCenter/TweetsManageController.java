package com.blog.controller.UserCenter;

import com.blog.pojo.Blog;
import com.blog.pojo.Tweet;
import com.blog.pojo.User;
import com.blog.service.BlogService;
import com.blog.service.TagService;
import com.blog.service.TweetService;
import com.blog.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/usercenter")
public class TweetsManageController {

    @Autowired
    private TweetService tweetService;
    @Autowired
    private TagService tagService;

    public void setTypeAndTag(Model model) {
//        model.addAttribute("types", typeService.getAllType());
        model.addAttribute("tags", tagService.getAllTag());
    }

    @GetMapping("/tweets")  //后台显示博客列表
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

//    @PostMapping("/blogs/search")  //按条件查询博客
//    public String searchBlogs(Blog blog, @RequestParam(required = false,defaultValue = "1",value = "pagenum")int pagenum, Model model){
//        PageHelper.startPage(pagenum, 5);
//        List<Tweet> allTweet = tweetService.searchAllTweet(tweet);
//        //得到分页结果对象
//        PageInfo pageInfo = new PageInfo(allTweet);
//        model.addAttribute("pageInfo", pageInfo);
//        model.addAttribute("message", "查询成功");
//        setTypeAndTag(model);
//        return "admin/blogs";
//    }
}
