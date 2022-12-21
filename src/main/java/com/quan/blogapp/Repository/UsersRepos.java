package com.quan.blogapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quan.blogapp.Entity.Users;

@Repository
public interface UsersRepos extends JpaRepository<Users, Long> {
    
}
