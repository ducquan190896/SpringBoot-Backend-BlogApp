package com.quan.blogapp.Service;

import java.util.List;

import javax.transaction.Transactional;

import com.quan.blogapp.Entity.Comment;
import com.quan.blogapp.Entity.Notification;

public interface CommentService {
    List<Comment> getCommentByPost(Long postId);
    List<Comment> getCommentByPostAndOwner(Long postId, Long OwnerId);
    List<Comment> getComments();
    Comment getComment(Long id);
    @Transactional
    void deleteComment(Long id);
    Notification likeComment(Long commentId);
    void unlikeComment(Long commentId);

}
