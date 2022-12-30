package com.quan.blogapp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quan.blogapp.Entity.Notification;
import com.quan.blogapp.Service.NotificationService;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {
    @Autowired
    NotificationService notificationService;

    @GetMapping("/all")
    public ResponseEntity<List<Notification>> getAll() {
        return new ResponseEntity<List<Notification>>(notificationService.getNotifications() , HttpStatus.OK);
    }
    @GetMapping("/allByReceiver/{id}")
    public ResponseEntity<List<Notification>> getAllByReceiver(@PathVariable Long id) {
        return new ResponseEntity<List<Notification>>(notificationService.getNotificationByReceiver(id) , HttpStatus.OK);
    }
    @GetMapping("/allBySender/{id}")
    public ResponseEntity<List<Notification>> getAllBySender(@PathVariable Long id) {
        return new ResponseEntity<List<Notification>>(notificationService.getNotificationBySender(id) , HttpStatus.OK);
    }

} 
