package com.blog.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.generator.RandomGenerator;
import com.blog.pojo.User;
import com.blog.service.UserService;
import org.apache.coyote.http11.HttpOutputBuffer;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class SignUpController {
//    @Autowired
    private LineCaptcha lineCaptcha;
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
    public String signUp(@RequestParam String code, User user,
                         HttpSession session,
                         RedirectAttributes attributes){
        String kaptcha =lineCaptcha.getCode();
        if (StringUtils.isEmpty(kaptcha) || StringUtils.isEmpty(code) || !kaptcha.equalsIgnoreCase(code)) {
            attributes.addFlashAttribute("msg", "验证码不正确!");
            return "redirect:/signup";//返回登录页面
        }
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

    @RequestMapping("/signup/getCode")
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
}
