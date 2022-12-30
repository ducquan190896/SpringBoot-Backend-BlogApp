package com.quan.blogapp.Service;

import java.util.List;

import com.quan.blogapp.Entity.Comment;
import com.quan.blogapp.Entity.Notification;
import com.quan.blogapp.Entity.NotificationType;
import com.quan.blogapp.Entity.Post;
import com.quan.blogapp.Entity.Users;

public interface NotificationService {
    Notification sendNotification(NotificationType type, Users sender, Users receiver, Post postNotify);
    Notification sendNotification(NotificationType type, Users sender, Users receiver, Comment comment);
    List<Notification> getNotifications();
    List<Notification> getNotificationBySender(Long senderId);
    List<Notification> getNotificationByReceiver(Long receiverId);
    

}
