package com.blog.controller;

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
@RequestMapping("/tweet")
public class TweetController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TweetService tweetService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    public void setTypeAndTag(Model model) {
        model.addAttribute("types", typeService.getAllType());
        model.addAttribute("tags", tagService.getAllTag());
    }

    @GetMapping("/blogs")  //后台显示博客列表
    public String blogs(@RequestParam(required = false,defaultValue = "1",value = "pagenum")int pagenum, Model model){
        PageHelper.startPage(pagenum, 5);
        List<Blog> allBlog = blogService.getAllBlog();
        List<Tweet> allTweet = tweetService.getAllTweet();
        //得到分页结果对象
        PageInfo pageInfo = new PageInfo(allBlog);
        model.addAttribute("pageInfo", pageInfo);
        setTypeAndTag(model);  //查询类型和标签
        return "admin/blogs";
    }

    @PostMapping("/blogs/search")  //按条件查询博客
    public String searchBlogs(Blog blog, @RequestParam(required = false,defaultValue = "1",value = "pagenum")int pagenum, Model model){
        PageHelper.startPage(pagenum, 5);
        List<Blog> allBlog = blogService.searchAllBlog(blog);
        //得到分页结果对象
        PageInfo pageInfo = new PageInfo(allBlog);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("message", "查询成功");
        setTypeAndTag(model);
        return "admin/blogs";
    }

    @GetMapping("/send") //去新增博客页面
    public String toAddBlog(Model model){
        model.addAttribute("blog", new Blog());  //返回一个blog对象给前端th:object
        setTypeAndTag(model);
        return "send-tweet";
    }

    @GetMapping("/{id}/edit") //去编辑博客页面
    public String toEditTweet(@PathVariable Long id, Model model){
        Tweet tweet = tweetService.getTweet(id);
//        tweet.init();   //将tags集合转换为tagIds字符串
        model.addAttribute("tweet", tweet);     //返回一个blog对象给前端th:object
        setTypeAndTag(model);
        return "tweet";
    }

    @PostMapping("/") //新增、编辑博客
    public String addTweet(Tweet tweet, HttpSession session, RedirectAttributes attributes){
        //设置user属性
        tweet.setUser((User) session.getAttribute("user"));
        //设置用户id
        tweet.setUserId(tweet.getUser().getId());
        //设置blog的type
//        tweet.setTypeId(typeService.getType(tweet.getType().getId()));
        //设置blog中typeId属性
//        tweet.setTypeId(tweet.getType().getId());
        //给blog中的List<Tag>赋值
//        tweet.setTags(tagService.getTagByString(tweet.getTagIds()));

        if (tweet.getId() == null) {   //id为空，则为新增
            tweetService.saveTweet(tweet);
        } else {
            tweetService.updateTweet(tweet);
        }

        attributes.addFlashAttribute("msg", "新增成功");
//        return "redirect:/admin/blogs";
        return "redirect:/";
    }

    @GetMapping("/{id}/delete")
    public String deleteTweet(@PathVariable Long id, RedirectAttributes attributes){
        tweetService.deleteTweet(id);
        attributes.addFlashAttribute("msg", "删除成功");
        return "redirect:/";
//        return "redirect:/admin/blogs";
    }
}
