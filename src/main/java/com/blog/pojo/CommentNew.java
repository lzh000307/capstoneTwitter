package com.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class CommentNew {
    private Long id;
    private String content;
    private Integer status;  //是否为管理员评论

    private Date createTime;

    private Long tweetId;
    private Long parentCommentId;  //父评论id
    private String parentNickname;

    //回复评论
    //private List<Comment> replyComments = new ArrayList<>();

    //父评论
    private CommentNew parentCommentNew;

    private Tweet tweet;

}
