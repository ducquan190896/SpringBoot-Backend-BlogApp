package com.quan.blogapp.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.quan.blogapp.Entity.Comment;
import com.quan.blogapp.Entity.Notification;
import com.quan.blogapp.Entity.Post;
import com.quan.blogapp.Entity.Users;
import com.quan.blogapp.Entity.Request.CommentRequest;
import com.quan.blogapp.Entity.Request.PostRequest;


public interface PostService {
    List<Post> getPosts();
    List<Post> getPostsByUsername(String username);
    Post getPost(Long id);
    @Transactional
    void deletePost(Long id);
    void createPost(PostRequest postRequest);
    // void addCommentToPost(Long postId, Comment comment);
    Notification likePost(Long postId);
    void removeLikePost(Long postId);
    List<Users> getLikeList(Long postId);
    Notification addComment(CommentRequest commentRequest);
   
}
