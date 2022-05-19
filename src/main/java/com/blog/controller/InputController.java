package com.blog.controller;

import com.blog.controller.entity.TweetForm;
import com.blog.pojo.Tweet;
import com.blog.pojo.User;
import com.blog.service.*;
import com.blog.util.Converter;
import com.blog.util.MinioUtilS;
import com.blog.util.TweetFrontEndConvector;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/tweet")
public class InputController {
    public final String REJECT = "redirect:/error/401";
    @Autowired
    private TweetService tweetService;
    @Autowired
    private Converter converter;
    @Autowired
    private TrendService trendService;
    @Autowired
    private TagService tagService;
    @Autowired
    private TweetImgService tweetImgService;
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

    @GetMapping("/send") //去新增博客页面
    public String toAddTweet(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            return "redirect:/login";
        }
//        //图片列表：
//        List<String> imgUnits = new ArrayList<>();
//        //丢进model
////        model.addAttribute("imgUnits", imgUnits);
        TweetForm tweetf = new TweetForm();
        model.addAttribute("tweetf", tweetf);  //返回一个tweet对象给前端th:object
        setTag(model);
        return "send-tweet";
    }

    @GetMapping("/{id}/edit") //去编辑博客页面
    public String toEditTweet(@PathVariable Long id, Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        Tweet tweet = tweetService.getTweet(id);
        if(user == null){
            return "redirect:/login";
        }
        //如果不是该用户所发的推文，则跳转到错误页
        if(tweet.getUserId().equals(user.getId())){
            List<String> imgUnits = tweetImgService.getImgUrl(id); //获取图片url
            model.addAttribute("imgUnits", imgUnits);
            model.addAttribute("tweet", tweet);     //返回一个tweet对象给前端th:object
            setTag(model);
            return "send-tweet";
        }
        return REJECT;
    }

    @GetMapping("/{id}/delete")
    public String deleteTweet(@PathVariable Long id, RedirectAttributes attributes, HttpSession session){
        User user = (User) session.getAttribute("user");
        Tweet tweet = tweetService.getTweet(id);
        if(tweet.getUserId().equals(user.getId())) {
            tweetService.deleteTweet(id);
            trendService.deleteByTweetId(id);
            attributes.addFlashAttribute("msg", "删除成功");
            return "redirect:/usercenter/tweets";
        }
        return REJECT;
    }

    @PostMapping("/") //新增、编辑博客
    public String addTweet(TweetForm tweetForm, HttpSession session, RedirectAttributes attributes){
        //设置user属性
        User user = (User) session.getAttribute("user"); //获取session中的user
        if(user.getId()==null){
            return "redirect:/login";
        }
        //处理上传图片
//        List<String> upload = minioUtilS.upload(tweetform.getImgs());
//        for(String url:upload){
//            System.out.println(address+"/"+bucketName+"/"+url);
//        }
        Tweet tweet = (Tweet) tweetForm;
        //设置用户id
        tweet.setUserId(user.getId());
        //设置权限类别
        tweet.setStatus(user.getStatus());
        if (tweet.getId() == null) {   //id为空，则为新增
            tweetService.saveTweet(tweet);
            //getTagIds: 从前端传回的String类型，like 1,2,3
            //convertToTagIds: 将String类型转换为List<Long>类型
            //updateTrends: 更新趋势
            trendService.updateTrends(converter.convertStringToTagIds(tweet.getTagIds()), tweet.getId());
        } else {
            tweetService.updateTweet(tweet);
            trendService.updateTrends(converter.convertStringToTagIds(tweet.getTagIds()), tweet.getId());
        }

        attributes.addFlashAttribute("msg", "新增成功");
        return "redirect:/";
    }

    @PostMapping("/imgUpload")
    public String uploadMultipartFile(@RequestParam("files") MultipartFile[] files, Model model) {
        List<String> fileNames = new ArrayList<>();
        try {
            if(null != files){
                List<String> upload = minioUtilS.upload(files);
                for(String url:upload) {
                    //编码
                    String encodedUrl = URLEncoder.encode(url, "UTF-8");
                    String imgUrl = address + "/" + bucketName + "/" + encodedUrl;
                    System.out.println(imgUrl);
                    fileNames.add(imgUrl);
                }
            }
            model.addAttribute("message", "Files uploaded successfully!");
            model.addAttribute("files", fileNames);
        } catch (Exception e) {
            model.addAttribute("message", "Fail!");
            model.addAttribute("files", fileNames);
        }

        return "send-tweet";
    }

    @GetMapping("/uploadTest")
    public String index() {
        return "uploadform";
    }



}