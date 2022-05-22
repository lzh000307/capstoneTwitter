package com.blog.controller.UserCenter;

import com.blog.pojo.User;
import com.blog.service.UserService;
import com.blog.util.MinioUtilS;
import com.sun.jndi.toolkit.url.UrlUtil;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserCenterController {
    @Autowired
    private MinioUtilS minioUtilS;
    @Value("${minio.endpoint}")
    private String address;
    @Value("${minio.bucketName}")
    private String bucketName;
    @Autowired
    private UserService userService;
    @GetMapping("/user/center")
    public String userCenterPage(Model model, HttpSession session) {
        // 获取当前登录用户
        User user = (User) session.getAttribute("user");
        //传回前端
        model.addAttribute("user", user);
        return "usercenter";
    }
    @GetMapping("/user/changepasswd")
    public String userChangePasswd(Model model, HttpSession session) {
        // 获取当前登录用户
        User user = (User) session.getAttribute("user");
        //传回前端
        model.addAttribute("user", user);
        return "changepassword";
    }
    @PostMapping("/user/saveprofile")
    public String userSaveProfile(@RequestParam("img") MultipartFile img, User user, HttpSession session, Model model, RedirectAttributes attributes) {
        // 获取当前登录用户
        User user1 = (User) session.getAttribute("user");
        //更新用户信息
        user1.setNickname(user.getNickname());
        user1.setEmail(user.getEmail());
        user1.setPhoneNumber(user.getPhoneNumber());
        //判断头像是否更改
        try {
            //坑：会传回来空值，并不是null值
            if(!img.isEmpty()){
                MultipartFile[] imgs = new MultipartFile[1];
                imgs[0] = img;
                List<String> uploadVideo = minioUtilS.upload(imgs);
                String encodedUrl = UrlUtil.encode(uploadVideo.get(0), "UTF-8");
                String imgUrl = address + "/" + bucketName + "/" + encodedUrl;
                //设置视频（数据库懒得改了）
                user1.setAvatar(imgUrl);
            }
//            model.addAttribute("message", "Files uploaded successfully!");

        } catch (Exception e) {
//            model.addAttribute("message", "Fail!");
        }
        attributes.addFlashAttribute("msg", "修改成功");
        //保存
        userService.update(user1);
        //更新session
        session.setAttribute("user", user1);
        return "redirect:/user/center";
    }
    @PostMapping("/user/changepswd")
    public String userChangePswd(@RequestParam("legacy_password") String oldpswd,
                                 @RequestParam("new_password") String newpswd, @RequestParam("repeat") String repeat,
                                 HttpSession session, RedirectAttributes attributes) {
        // 获取当前登录用户
        User user_now = (User) session.getAttribute("user");
        User user = userService.checkUser(user_now.getUsername(), oldpswd);
        //密码错误
        if(user == null){
            attributes.addFlashAttribute("msg_n", "用户名或密码错误");
            return "redirect:/user/changepasswd";
        }
        //密码一致
        //确认输入的密码不一致
        if(!newpswd.equals(repeat)){
            attributes.addFlashAttribute("msg_n", "两次输入的新密码不一致");
            return "redirect:/user/changepasswd";
        }
        //更改密码
        userService.changePswd(user_now.getId(), newpswd);
        attributes.addFlashAttribute("msg", "密码修改成功");
        return "redirect:/user/changepasswd";
    }
}
