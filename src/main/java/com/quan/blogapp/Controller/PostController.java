package com.quan.blogapp.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quan.blogapp.Entity.Notification;
import com.quan.blogapp.Entity.Post;
import com.quan.blogapp.Entity.Users;
import com.quan.blogapp.Entity.Request.CommentRequest;
import com.quan.blogapp.Entity.Request.PostRequest;
import com.quan.blogapp.Service.PostService;


@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    PostService postService;


    @GetMapping("/all")
    public ResponseEntity<List<Post>> getAll() {
        return new ResponseEntity<List<Post>>(postService.getPosts(), HttpStatus.OK);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<List<Post>> getAllByUsername(@PathVariable String username) {
        return new ResponseEntity<List<Post>>(postService.getPosts(), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Post> getpost(@PathVariable Long id) {
        return new ResponseEntity<Post>(postService.getPost(id), HttpStatus.OK);
    }

    @GetMapping("/getLikedlist/{postId}")
    public ResponseEntity<List<Users>> getlikedUsers(@PathVariable Long postId) {
        return new ResponseEntity<List<Users>>(postService.getLikeList(postId), HttpStatus.OK);
    }

    @PutMapping("/likePost/{postId}")
    public ResponseEntity<Notification> likePost(@PathVariable Long postId) {
        
        return new ResponseEntity<>( postService.likePost(postId), HttpStatus.OK);
    }

    @PutMapping("/removelikepost/{postId}")
    public ResponseEntity<HttpStatus> removelikePost(@PathVariable Long postId) {
        postService.removeLikePost(postId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<HttpStatus> createPost(@Valid @RequestBody PostRequest postRequest) {
        postService.createPost(postRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<Notification> addCommentToPost(@Valid @RequestBody CommentRequest commentRequest) {
        return new ResponseEntity<Notification>(postService.addComment(commentRequest), HttpStatus.OK);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<HttpStatus> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
