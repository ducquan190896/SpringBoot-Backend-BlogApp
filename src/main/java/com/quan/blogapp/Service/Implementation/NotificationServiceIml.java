package com.quan.blogapp.Service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quan.blogapp.Entity.Comment;
import com.quan.blogapp.Entity.Notification;
import com.quan.blogapp.Entity.NotificationType;
import com.quan.blogapp.Entity.Post;
import com.quan.blogapp.Entity.Users;
import com.quan.blogapp.Repository.NotificationRepos;
import com.quan.blogapp.Service.NotificationService;


@Service
public class NotificationServiceIml implements NotificationService {
    @Autowired
    NotificationRepos notificationRepos;

    @Override
    public Notification sendNotification(NotificationType type, Users sender, Users receiver, Post postNotify) {
        Notification notification = new Notification(type, sender, receiver, postNotify);
        return notificationRepos.save(notification);
    }

    @Override
    public Notification sendNotification(NotificationType type, Users sender, Users receiver, Comment comment) {
        Notification notification = new Notification(type, sender, receiver, comment);
        return notificationRepos.save(notification);
    }

  

   

    
}
