package com.quan.blogapp.Service.Implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quan.blogapp.Entity.Comment;
import com.quan.blogapp.Entity.Notification;
import com.quan.blogapp.Entity.NotificationType;
import com.quan.blogapp.Entity.Post;
import com.quan.blogapp.Entity.Users;
import com.quan.blogapp.Repository.NotificationRepos;
import com.quan.blogapp.Service.NotificationService;
import com.quan.blogapp.Service.UsersService;


@Service
public class NotificationServiceIml implements NotificationService {
    @Autowired
    NotificationRepos notificationRepos;
    @Autowired
    UsersService usersService;

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

    @Override
    public List<Notification> getNotificationByReceiver(Long receiverId) {
    Users receiver = usersService.getUser(receiverId);
      return notificationRepos.findNotificationByReciever(receiver);
    }

    @Override
    public List<Notification> getNotificationBySender(Long senderId) {
        Users sender = usersService.getUser(senderId);
        return notificationRepos.findNotificationBySender(sender);
    }

    @Override
    public List<Notification> getNotifications() {
        return notificationRepos.findAll();
    }

  

   

    
}
