package com.blog.controller.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorController {
    @GetMapping("/401")
    public String error401(){
        return "/error/401";
    }
    @GetMapping("/404")
    public String error404(){
        return "/error/404";
    }
    @GetMapping("/500")
    public String error500(){
        return "/error/500";
    }

}
