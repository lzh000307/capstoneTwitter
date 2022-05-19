package com.blog.controller.admin;

import com.blog.pojo.Tweet;
import com.blog.pojo.User;
import com.blog.pojo.UserCollection;
import com.blog.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class UserManageController {
    public final String REJECT = "redirect:/error/401";
    public final Integer CENSOR = 2001;     //夹
    public final Integer BAN = 2002;        //封禁
    @Autowired
    private UserService userService;

    @GetMapping("/usermanage")  //显示自己的收藏列表
    public String userManagement(@RequestParam(required = false,defaultValue = "1",value = "pagenum")int pagenum, Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        //用户为空，跳转到登录页面
        if(user == null){
            return "redirect:/login";
        }
        //用户没权限，跳转REJECT
        if(user.getStatus() != 1000){
            return REJECT;
        }
        PageHelper.startPage(pagenum, 8);
        //取得用户列表
        List<User> users = userService.getAllUser();
        //得到分页结果对象
        PageInfo pageInfo = new PageInfo(users);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/usermanage";
    }

    @GetMapping("/ban/{id}")  //显示自己的收藏列表
    public String banUser(@PathVariable Long id, HttpSession session){
        User user = (User) session.getAttribute("user");
        //用户为空，跳转到登录页面
        if(user == null){
            return "redirect:/login";
        }
        //用户没权限，跳转REJECT
        if(user.getStatus() != 1000){
            return REJECT;
        }
        if(!userService.findById(id).getStatus().equals(BAN)) {
            userService.setStatus(id, BAN);
        }else{
            userService.setStatus(id, 1);
        }
        return "redirect:/admin/usermanage";
    }

    @GetMapping("/censor/{id}")  //显示自己的收藏列表
    public String censorUserTweets(@PathVariable Long id, HttpSession session){
        User user = (User) session.getAttribute("user");
        //用户为空，跳转到登录页面
        if(user == null){
            return "redirect:/login";
        }
        //用户没权限，跳转REJECT
        if(user.getStatus() != 1000){
            return REJECT;
        }
        if(!userService.findById(id).getStatus().equals(CENSOR)) {
            userService.setStatus(id, CENSOR);
        }else{
            userService.setStatus(id, 1);
        }
        return "redirect:/admin/usermanage";
    }
}
