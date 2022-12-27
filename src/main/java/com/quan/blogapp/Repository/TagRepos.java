package com.quan.blogapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quan.blogapp.Entity.Tag;

@Repository
public interface TagRepos extends JpaRepository<Tag, Long> {
    
}
