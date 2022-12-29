package com.quan.blogapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quan.blogapp.Entity.Notification;

@Repository
public interface NotificationRepos extends JpaRepository<Notification, Long> {
    
}
