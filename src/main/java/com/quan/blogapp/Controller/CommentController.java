package com.quan.blogapp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quan.blogapp.Entity.Comment;
import com.quan.blogapp.Entity.Notification;
import com.quan.blogapp.Service.CommentService;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    CommentService commentService;

    @GetMapping("/all")
    public ResponseEntity<List<Comment>> getAll() {
        return new ResponseEntity<List<Comment>>(commentService.getComments(), HttpStatus.OK);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<Comment>> getAllByPost(@PathVariable Long postId) {
        return new ResponseEntity<List<Comment>>(commentService.getCommentByPost(postId), HttpStatus.OK);
    }

    @GetMapping("/post/{postId}/postOwner/{ownerId}")
    public ResponseEntity<List<Comment>> getAllByPost(@PathVariable Long postId, @PathVariable Long ownerId) {
        return new ResponseEntity<List<Comment>>(commentService.getCommentByPostAndOwner(postId, ownerId), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Comment> getPost(@PathVariable Long id) {
        return new ResponseEntity<>(commentService.getComment(id), HttpStatus.OK);
    }

    @PutMapping("/likeComment/{id}")
    public ResponseEntity<Notification> likeComment(@PathVariable Long id) {
        return new ResponseEntity<Notification>(commentService.likeComment(id), HttpStatus.OK);
    }

    @PutMapping("/unlikeComment/{id}")
    public ResponseEntity<HttpStatus> unlikeComment(@PathVariable Long id) {
        commentService.unlikeComment(id);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<HttpStatus> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return new ResponseEntity<HttpStatus>( HttpStatus.NO_CONTENT);
    }
}
