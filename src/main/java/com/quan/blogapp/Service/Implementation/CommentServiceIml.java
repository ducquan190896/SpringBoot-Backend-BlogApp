package com.quan.blogapp.Service.Implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.quan.blogapp.Entity.Comment;
import com.quan.blogapp.Entity.Notification;
import com.quan.blogapp.Entity.NotificationType;
import com.quan.blogapp.Entity.Post;
import com.quan.blogapp.Entity.Users;
import com.quan.blogapp.Exception.EntityNotFoundException;
import com.quan.blogapp.Repository.CommentRepos;
import com.quan.blogapp.Repository.PostRepos;
import com.quan.blogapp.Repository.UsersRepos;
import com.quan.blogapp.Service.CommentService;
import com.quan.blogapp.Service.NotificationService;
import com.quan.blogapp.Service.PostService;

@Service
public class CommentServiceIml implements CommentService {
    @Autowired
    CommentRepos commentRepos;
    @Autowired
    UsersRepos usersRepos;
    @Autowired
    NotificationService notificationService;
    @Autowired
   PostService postService;
   @Autowired
   PostRepos postRepos;


    @Override
    public void deleteComment(Long id) {
       String username = SecurityContextHolder.getContext().getAuthentication().getName();
       Optional<Users> entityUser = usersRepos.findByUsername(username);
       if(!entityUser.isPresent()) {
        throw new EntityNotFoundException("the current user with username " + username + " not found");
       }
       Users authUser = entityUser.get();
       Optional<Comment> entityComment = commentRepos.findById(id);
       Comment comment = isCheck(entityComment, id);
       
       if(!comment.getCommentOwner().equals(authUser)) {
        throw new EntityNotFoundException("your account cannot delete this comment because you are not the owner of the commnet");
       }
       // test this one

    //    authUser.getComments().remove(comment);
    //    usersRepos.save(authUser);
    //    Post post = comment.getPost();
    //    post.deleteComment(comment);
    //    postRepos.save(post);
       commentRepos.delete(comment);
        
    }
    @Override
    public Comment getComment(Long id) {
        Optional<Comment> entity = commentRepos.findById(id);
        Comment comment = isCheck(entity, id);
        return comment;
    }
    @Override
    public List<Comment> getCommentByPost(Long postId) {
        Post post = postService.getPost(postId);
        return commentRepos.findCommentByPost(post);
    }
    @Override
    public List<Comment> getCommentByPostAndOwner(Long postId, Long OwnerId) {
        Post post = postService.getPost(postId);
        Optional<Users> entity = usersRepos.findById(OwnerId);
        if(!entity.isPresent()) {
            throw new EntityNotFoundException("the owner of comment with id " + OwnerId + " not found");
        }
        Users user = entity.get();
        return commentRepos.findCommentByPostAndCommentOwner(post, user);
    }
    @Override
    public List<Comment> getComments() {
        return commentRepos.findAll();
    }
    @Override
    public Notification likeComment(Long commentId) {
        Optional<Comment> entityComment = commentRepos.findById(commentId);
        Comment comment = isCheck(entityComment, commentId);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Users> entityUser = usersRepos.findByUsername(username);
        if(!entityUser.isPresent()) {
         throw new EntityNotFoundException("the current user with username " + username + " not found");
        }
        Users authUser = entityUser.get();
        if(comment.getCommentlikes().contains(authUser)) {
            throw new EntityNotFoundException("you already liked this comment, cannot like twice");
        }
        comment.likeComment(authUser);
        commentRepos.save(comment);
        usersRepos.save(authUser);

        return notificationService.sendNotification(NotificationType.CommentLike, authUser, comment.getCommentOwner(), comment);

    }
   
    @Override
    public void unlikeComment(Long commentId) {
        Optional<Comment> entityComment = commentRepos.findById(commentId);
        Comment comment = isCheck(entityComment, commentId);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Users> entityUser = usersRepos.findByUsername(username);
        if(!entityUser.isPresent()) {
         throw new EntityNotFoundException("the current user with username " + username + " not found");
        }
        Users authUser = entityUser.get();
        if(!comment.getCommentlikes().contains(authUser)) {
            throw new EntityNotFoundException("you have not liked this comment, cannot remove like");
        }
        comment.removeLikeComment(authUser);
        commentRepos.save(comment);
        usersRepos.save(authUser);
        
    }

    private Comment isCheck(Optional<Comment> entity, Long id) {
        if(entity.isPresent()) {
            return entity.get();
        }
        throw new EntityNotFoundException("the comment with id " + id + " not found");
    }
    
}
