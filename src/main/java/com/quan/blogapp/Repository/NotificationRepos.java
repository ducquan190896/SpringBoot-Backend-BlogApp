package com.quan.blogapp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.quan.blogapp.Entity.Notification;
import com.quan.blogapp.Entity.Users;

@Repository
public interface NotificationRepos extends JpaRepository<Notification, Long> {
    
    @Query(
        value = "select * from notification where receiver = ?",
        nativeQuery = true
    )
    List<Notification> findNotificationByReciever(Users receiver);

    @Query(
        value = "select * from notification where sender = ?",
        nativeQuery = true
    )
    List<Notification> findNotificationBySender(Users sender);
}
