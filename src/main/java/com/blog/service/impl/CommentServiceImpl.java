package com.blog.service.impl;

import com.blog.dao.BlogDao;
import com.blog.dao.CommentDao;
import com.blog.pojo.Censorship;
import com.blog.pojo.Comment;
import com.blog.pojo.Tweet;
import com.blog.service.CensorshipService;
import com.blog.service.CommentService;
import com.blog.service.UserService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;
    @Autowired
    private CensorshipService censorshipService; // 评论审核
    @Autowired
    private UserService userService;

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
        //检测敏感词
        int status = censorshipService.censor(comment.getContent());
        if(status > 0){
            status = status + 4000;
            comment.setStatus(status);
            return commentDao.insert(comment);
        }
        return commentDao.insert(comment);
    }

    @Override
    public void censorshipAPI(Censorship censorship) {
        List<Comment> comments = commentDao.getAll();
        String word = censorship.getWord();
        Integer status = censorship.getId() + 4000;
        //查找所有包含该词的推文
        for (Comment comment : comments) {
            if(comment.getContent().contains(word)){
                comment.setStatus(status);
                commentDao.update(comment);
            }
        }
    }

    @Override
    public void removeCensorship(int id) {
        Integer status = id + 4000;
        List<Comment> comments = commentDao.queryByStatus(id+4000);
        //恢复推文
        for (Comment comment : comments) {
            comment.setStatus(userService.findById(comment.getUserId()).getStatus());
            //反查有无其他敏感词
            int newStatus = censorshipService.censor(comment.getContent());
            if(newStatus>0){
                newStatus = newStatus + 4000;
                comment.setStatus(newStatus);
            }
            //更新推文
            commentDao.update(comment);
        }
    }

}
