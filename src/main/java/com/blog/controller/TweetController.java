package com.blog.controller;

import com.blog.controller.entity.TweetFrontEnd;
import com.blog.pojo.Tweet;
import com.blog.pojo.User;
import com.blog.service.*;
import com.blog.util.Converter;
import com.blog.util.MinioUtilS;
import com.blog.util.TweetFrontEndConvector;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/tweet")
public class TweetController {

    public final String REJECT = "redirect:/error/401";
    @Autowired
    private TweetService tweetService;

    @Autowired
    private Converter converter;
    @Autowired
    private TrendService trendService;
    @Autowired
    private LikeService likeService;
    @Autowired
    private TagService tagService;
    @Autowired
    private UserCollectionService userCollectionService;
    @Autowired
    private TweetFrontEndConvector tweetFrontEndConvector;
    @Autowired
    private MinioUtilS minioUtilS;
    @Value("${minio.endpoint}")
    private String address;
    @Value("${minio.bucketName}")
    private String bucketName;

    public void setTag(Model model) {
        model.addAttribute("tags", tagService.getAllTag());
    }
    @GetMapping("/likes/{id}")
    public String likes(@PathVariable Long id, HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        Tweet tweet = tweetService.getTweet(id);
        //如果已经点赞，则取消点赞
        if(likeService.isLike(user.getId(), id)){
            tweet.setLikes(tweet.getLikes() - 1);
            likeService.deleteLike(user.getId(), id);
        }else {
            tweet.setLikes(tweet.getLikes() + 1);
            likeService.addLike(user.getId(), id);
        }
        tweetService.updateTweet(tweet);
        return "redirect:/tweet/" + id;
    }

    @GetMapping("/collections/{id}")
    public String collection(@PathVariable Long id, HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        Tweet tweet = tweetService.getTweet(id);
        //如果已经收藏，则取消收藏
        if(userCollectionService.isUserCollection(user.getId(), id)){
            userCollectionService.deleteUserCollection(user.getId(), id);
        }else {
            userCollectionService.addUserCollection(user.getId(), id);
        }
        return "redirect:/tweet/" + id;
    }

    @GetMapping("/{id}")
    public String surfTweet(@PathVariable Long id, Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        //判断是否被屏蔽，以及是否仅自己可见
        Tweet tweet = tweetService.getTweet(id);
        Integer status = tweet.getStatus();
        if(status > 2000 || tweet.isPublished()==false){
            if(user == null || !user.getId().equals(tweet.getUserId())){
                return Constant.REJECT;
            }
        }
        //没被屏蔽的情况
        boolean isCollection = false; //是否收藏
        boolean isLike = false;
        tweetService.viewPlusOne(tweet);
        TweetFrontEnd tweetFE = tweetFrontEndConvector.convertToTweetFrontEnd(tweet);
        if(user != null){
            isCollection = userCollectionService.isUserCollection(user.getId(), id);
            isLike = likeService.isLike(user.getId(), id);
        }
        model.addAttribute("tweetfe", tweetFE);
        model.addAttribute("isCollection", isCollection);
        model.addAttribute("isLike", isLike);
        return "tweet";
    }


}
