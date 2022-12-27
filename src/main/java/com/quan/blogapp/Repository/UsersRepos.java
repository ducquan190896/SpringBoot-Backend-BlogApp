package com.quan.blogapp.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.quan.blogapp.Entity.Users;

@Repository
public interface UsersRepos extends JpaRepository<Users, Long> {
    
    @Query(
        value = "select * from users where username = ?",
        nativeQuery = true
    )
    Optional<Users> findByUsername(String username);
}
