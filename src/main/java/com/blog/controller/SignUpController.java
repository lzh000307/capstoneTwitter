package com.blog.controller;

import com.blog.pojo.User;
import com.blog.service.UserService;
import org.apache.coyote.http11.HttpOutputBuffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
@Controller
public class SignUpController {

    @Autowired
    private UserService userService;

    @GetMapping("/signup")
    public String signUpPage(HttpSession session, Model model){
        //如果没登录
        if(session.getAttribute("user") == null) {
            model.addAttribute("user",new User());
            return "sign-up";
        }
        //如果登录了
        return "redirect:/"; //跳转到主页
    }

    @PostMapping("/signup")
    public String signUp(User user,
                        HttpSession session,
                        RedirectAttributes attributes){
        if(user.getUsername()==null || userService.existUsername(user.getUsername())){
            attributes.addFlashAttribute("msg","用户名已存在，请重新输入");
            return "redirect:/signup";
        }else {
            userService.signUp(user);
            //删掉密码
            user.setPassword(null);
            session.setAttribute("user", user);
            return "redirect:/";
        }
    }
}
