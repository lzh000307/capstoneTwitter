package com.blog.controller;

import com.blog.controller.entity.CommentFrontEnd;
import com.blog.controller.entity.TweetFrontEnd;
import com.blog.controller.service.CommentFrontEndService;
import com.blog.pojo.Comment;
import com.blog.pojo.CommentLe;
import com.blog.pojo.User;
import com.blog.service.BlogService;
import com.blog.service.CommentService;
import com.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class CommentController {

    @Autowired
    private CommentFrontEndService commentFrontEndService;

    @Autowired
    private CommentService commentService;
    @Autowired
    private BlogService blogService;
    @Autowired
    UserService userService;

    @Value("${comment.avatar}")
    private String avatar;

    @GetMapping("/comments/{tweetId}")  //展示留言
    public String comments(@PathVariable Long tweetId, Model model){
        model.addAttribute("comments", commentFrontEndService.passToFront(tweetId));
//        model.addAttribute("blog", blogService.getDetailedBlog(blogId));
        return "tweet :: commentList";
    }

    @PostMapping("/comments")   //提交留言
    public String post(Comment comment, HttpSession session){
        Long tweetId = comment.getTweetId();
//        comment.setBlog(blogService.getDetailedBlog(blogId));  //绑定博客与评论
//        comment.setBlogId(blogId);
        User user = (User) session.getAttribute("user");
        //防止篡改？好像session不会被篡改草
//        User user = userService.findById(temp.getId());
        System.out.println(comment);
        //校验一下有没有用户
        if (user != null){
            //设置用户ID
            comment.setUserId(user.getId());
            //设置权限
            comment.setStatus(user.getStatus());
            //设置replayUserId
            //坑1：Long类型不能直接比较
            if(!comment.getParentCommentId().equals(-1L)){
                //如果不是主评论，则需找到主评论的ID
                //找到父评论
                Comment mainComment = commentService.findById(comment.getParentCommentId());
                //首先设置回复的UserId
                comment.setReplyUserId(commentService.findById(mainComment.getId()).getUserId());
                //坑2：要递归到主评论再save，多叉树扁平化
                //当父评论也不是主评论时：
                while(!mainComment.getParentCommentId().equals(-1L)){
                    //继续递归，直到找到主评论
                    mainComment = commentService.findById(mainComment.getParentCommentId());
                }
                //现在的mainComment是主评论了，即getParentCommentId()==-1L，设置ParentCommentId
                comment.setParentCommentId(mainComment.getId());
            }
            commentService.saveComment(comment);
        }
        return "redirect:/comments/" + tweetId;
    }
}
