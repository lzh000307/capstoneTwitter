package com.blog.service.impl;

import com.blog.dao.BlogDao;
import com.blog.dao.CommentDao;
import com.blog.pojo.Comment;
import com.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private BlogDao blogDao;

    /**
     * 查询主评论
     * @param tweetId
     * @return
     */
    @Override
    public List<Comment> getMainComment(Long tweetId) {  //查询父评论
        //没有父节点的默认为-1
        List<Comment> comments = commentDao.selectMainCommentByTweetId(tweetId);
        return comments;
    }

    /**
     * 查询子评论
     * @param commentId
     * @return
     */
    @Override
    public List<Comment> getSubComment(Long commentId) {  //查询子评论
        List<Comment> comments = commentDao.selectSubCommentByParentCommentId(commentId);
        return comments;
    }

    @Override
    public Comment findById(Long commentId) {
        return commentDao.queryById(commentId);
    }

    /**
     * 保存评论，传回的可能不是Comment; save comments, but param might not be Comment//TODO: Convector
     * @param comment
     * @return
     */
    @Override
    //接收回复的表单
    public int saveComment(Comment comment) {
        //获得父id
//        Long parentCommentId = comment.getParentCommentId();
        comment.setCreateTime(new Date());
        //没有父级评论默认是-1
//        if (parentCommentId != -1) {
//            //有父级评论
//            comment.setParentComment(commentDao.findByParentCommentId(comment.getParentCommentId()));
//        } else {
//            //没有父级评论
//            comment.setParentCommentId((long) -1);
//            comment.setParentComment(null);
//        }
        return commentDao.insert(comment);
    }

}
