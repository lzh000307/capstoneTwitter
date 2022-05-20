package com.blog.controller.legacy;

import org.springframework.web.bind.annotation.GetMapping;

//@Controller
public class AboutShowController {

    @GetMapping("/about")
    public String about(){
        return "about";
    }
}
