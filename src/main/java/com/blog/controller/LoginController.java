package com.blog.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.generator.RandomGenerator;
import com.blog.pojo.User;
import com.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class LoginController {
    LineCaptcha lineCaptcha;
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        @RequestParam String code,
                        HttpSession session,
                        RedirectAttributes attributes){
        String kaptcha =lineCaptcha.getCode();
        if (StringUtils.isEmpty(kaptcha) || StringUtils.isEmpty(code) || !kaptcha.equalsIgnoreCase(code)) {
            attributes.addFlashAttribute("msg", "验证码不正确!");
            return "redirect:/login";//返回登录页面
        }
        User user = userService.checkUser(username, password);
        if(user == null){
            attributes.addFlashAttribute("msg", "用户名或密码错误");
            return "redirect:/login";
        }
        if(user.getStatus().equals(Constant.BAN)){
            attributes.addFlashAttribute("msg", "该用户已被封禁");
            return "redirect:/login";
        }
        user.setPassword(null);
        session.setAttribute("user", user);
        return "redirect:/";
    }

    @RequestMapping("/login/getCode")
    public void getCode(HttpServletResponse response) {
        // 随机生成 4 位验证码
        RandomGenerator randomGenerator = new RandomGenerator("0123456789", 4);
        // 定义图片的显示大小
        lineCaptcha = CaptchaUtil.createLineCaptcha(110, 36);
        response.setContentType("image/jpeg");
        response.setHeader("Pragma", "No-cache");
        try {
            // 调用父类的 setGenerator() 方法，设置验证码的类型
            lineCaptcha.setGenerator(randomGenerator);
            // 输出到页面
            lineCaptcha.write(response.getOutputStream());
            // 关闭流
            response.getOutputStream().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/";
    }
}
