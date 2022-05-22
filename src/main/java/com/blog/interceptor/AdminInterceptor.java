package com.blog.interceptor;

import com.blog.pojo.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//登录拦截器
public class AdminInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取session中的user
        User user = (User) request.getSession().getAttribute("user");
        if (user == null){
            response.sendRedirect("/login");
            return false;
        }
        if(user.getStatus()==1000) {
            return true;
        }
        response.sendRedirect("/error/401");
        return false;
    }
}
