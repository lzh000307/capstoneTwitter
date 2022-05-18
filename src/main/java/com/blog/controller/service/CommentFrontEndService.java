package com.blog.controller.service;

import com.blog.controller.entity.CommentFrontEnd;
import com.blog.pojo.Comment;
import com.blog.service.CommentService;
import com.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentFrontEndService {
    @Autowired
    CommentService commentService;
    @Autowired
    UserService userService;

    public List<CommentFrontEnd> passToFront(Long TweetId){
        //获取主评论
        List<Comment> comments = commentService.getMainComment(TweetId);
        //转换为commentFrontEnd
        List<CommentFrontEnd> commentFrontEnds = ConvertCommentToFrontEnd(comments);
        //填充reply
        commentFrontEnds = fillReplies(commentFrontEnds);
        return commentFrontEnds;
    }

    public CommentFrontEnd ConvertCommentToFrontEnd(Comment comment){
        CommentFrontEnd commentFrontEnd = new CommentFrontEnd();
        commentFrontEnd.setComment(comment);
        commentFrontEnd.setUser(userService.findById(comment.getUserId()));
        //如果是主评论
        if(comment.getParentCommentId().equals(-1L)){
            commentFrontEnd.setMainComment(true);
        }else{
            //如果是子评论
            //如果有回复人，其实只要是回复，就一定会有回复人
            if(comment.getReplyUserId() != null) {
                //设置用户名（其实是nickname)，这里逻辑有点混乱，@的回复应该为用户名，但是主页面显示的都是nickname，所以为了统一暂时使用nickname
                commentFrontEnd.setReplyUsername(userService.findById(comment.getReplyUserId()).getNickname());
            }
        }
        return commentFrontEnd;
    }

    public List<CommentFrontEnd> ConvertCommentToFrontEnd(List<Comment> comments){
        List<CommentFrontEnd> commentFrontEnds = new ArrayList<>();
        for(Comment comment : comments){
            commentFrontEnds.add(ConvertCommentToFrontEnd(comment));
        }
        return commentFrontEnds;
    }

    /**
     * 填充主列表中的reply
     * @param commentFrontEnds
     * @return
     */
    public List<CommentFrontEnd> fillReplies(List<CommentFrontEnd> commentFrontEnds){
        for(CommentFrontEnd commentFrontEnd : commentFrontEnds){
            //获取subComment
            List<Comment> subComments = commentService.getSubComment(commentFrontEnd.getComment().getId());
            //转换为subCommentFrontEnd
            List<CommentFrontEnd> subCommentFrontEnds = ConvertCommentToFrontEnd(subComments);
            //填充reply
            commentFrontEnd.setReplyComments(subCommentFrontEnds);
        }
        return commentFrontEnds;
    }
}
