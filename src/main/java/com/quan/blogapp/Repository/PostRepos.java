package com.quan.blogapp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quan.blogapp.Entity.Post;
import com.quan.blogapp.Entity.Users;

@Repository
public interface PostRepos extends JpaRepository<Post, Long> {
    
    List<Post> findPostByPostOwner(Users user);
}
