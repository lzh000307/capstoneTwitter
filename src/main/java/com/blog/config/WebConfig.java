package com.blog.config;

import com.blog.interceptor.AdminInterceptor;
import com.blog.interceptor.UserInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {   //配置拦截器
        registry.addInterceptor(new AdminInterceptor())
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin")
                .excludePathPatterns("/admin/login");
        registry.addInterceptor(new UserInterceptor())
                .addPathPatterns("/user/**")
                .addPathPatterns("/usercenter/**")
                .excludePathPatterns("/user/id/**");  //排除拦截
    }
}
