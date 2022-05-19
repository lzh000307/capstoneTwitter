package com.blog.controller.UserCenter;

import com.blog.pojo.User;
import com.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpSession;

@Controller
public class UserCenterController {
    @Autowired
    private UserService userService;

    @GetMapping("/user/center")
    public String userCenterPage(Model model, HttpSession session) {
        // 获取当前登录用户
        User user = (User) session.getAttribute("user");
        //验证一下user是否为空
        if(user == null) {
            return "redirect:/login";
        }
        //传回前端
        model.addAttribute("user", user);
        return "usercenter";
    }
    @GetMapping("/user/changepasswd")
    public String userChangePasswd(Model model, HttpSession session) {
        // 获取当前登录用户
        User user = (User) session.getAttribute("user");
        //验证一下user是否为空
        if(user == null) {
            return "redirect:/login";
        }
        //传回前端
        model.addAttribute("user", user);
        return "changepassword";
    }
}
