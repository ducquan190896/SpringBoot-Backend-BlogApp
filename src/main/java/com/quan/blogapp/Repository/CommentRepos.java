package com.quan.blogapp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.quan.blogapp.Entity.Comment;
import com.quan.blogapp.Entity.Post;
import com.quan.blogapp.Entity.Users;

@Repository
public interface CommentRepos  extends JpaRepository<Comment, Long>{
    
    
    List<Comment> findCommentByPostAndCommentOwner(Post post, Users user);
    List<Comment> findCommentByPost(Post post);
}
