package com.blog.service;

import com.blog.pojo.Comment;

import java.util.List;

public interface CommentService {
    public List<Comment> getMainComment(Long tweetId);
    public int saveComment(Comment comment);
    public List<Comment> getSubComment(Long commentId);

    Comment findById(Long commentId);
}
