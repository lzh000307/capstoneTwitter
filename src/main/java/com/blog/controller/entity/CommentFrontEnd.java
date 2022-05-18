package com.blog.controller.entity;

import com.blog.pojo.Comment;
import com.blog.pojo.User;

import java.util.List;

public class CommentFrontEnd {
    private Comment comment;
    private User user;
    private String replyUsername; // 回复的用户名
    private Boolean isMainComment = false; // 是否是主评论
    private List<CommentFrontEnd> replyComments; // 回复的评论

    public Boolean isMainComment() {
        return isMainComment;
    }

    public void setMainComment(Boolean mainComment) {
        isMainComment = mainComment;
    }

    public List<CommentFrontEnd> getReplyComments() {
        return replyComments;
    }

    public void setReplyComments(List<CommentFrontEnd> replyComments) {
        this.replyComments = replyComments;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getReplyUsername() {
        return replyUsername;
    }

    public void setReplyUsername(String replyUsername) {
        this.replyUsername = replyUsername;
    }
}
