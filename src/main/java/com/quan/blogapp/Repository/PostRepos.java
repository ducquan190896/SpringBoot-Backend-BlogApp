package com.quan.blogapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quan.blogapp.Entity.Post;

@Repository
public interface PostRepos extends JpaRepository<Post, Long> {
    
}
