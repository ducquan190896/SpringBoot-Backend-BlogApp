package com.quan.blogapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quan.blogapp.Entity.Comment;

@Repository
public interface CommentRepos  extends JpaRepository<Comment, Long>{
    
}
