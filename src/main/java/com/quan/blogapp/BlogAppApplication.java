package com.quan.blogapp;

import java.util.List;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.quan.blogapp.Entity.Comment;
import com.quan.blogapp.Entity.Notification;
import com.quan.blogapp.Entity.NotificationType;
import com.quan.blogapp.Entity.Post;
import com.quan.blogapp.Entity.Role;
import com.quan.blogapp.Entity.Tag;
import com.quan.blogapp.Entity.Users;
import com.quan.blogapp.Repository.CommentRepos;
import com.quan.blogapp.Repository.NotificationRepos;
import com.quan.blogapp.Repository.PostRepos;
import com.quan.blogapp.Repository.TagRepos;
import com.quan.blogapp.Repository.UsersRepos;

@SpringBootApplication
public class BlogAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(UsersRepos usersRepos, PostRepos postRepos, CommentRepos commentRepos, TagRepos tagRepos, NotificationRepos notificationRepos) {
		return args -> {
		// Users admin = new Users("quan", new BCryptPasswordEncoder().encode("123456"), Role.ADMIN);
		// Users user1 = new Users("quanUser",  new BCryptPasswordEncoder().encode("123456"), Role.USER);
		// Users user2 = new Users("khanh",  new BCryptPasswordEncoder().encode("123456"), Role.USER);
		// usersRepos.save(admin);
		// usersRepos.save(user1);
		// usersRepos.save(user2);

		// // user2.setUsername("khanh khung");
		// // usersRepos.save(user2);
	

		// Post post1 = new Post("hello post", "jlkasjdf.png", user1);
		// postRepos.save(post1);

		
		 
		// // post1.likePost(admin);
		// // post1.likePost(user2);
		// Notification notify1 = new Notification(NotificationType.PostLike, user1, user2, post1);
		
		// notificationRepos.save(notify1);
		//  postRepos.save(post1);

		//  user1.getFollowedsBy().add(user2);
		//  user2.getFollowings().add(user1);
		//  user2.getFollowings().add(admin);
		//  admin.getFollowedsBy().add(user2);
		//  usersRepos.save(admin);
		//  usersRepos.save(user1);
		//  usersRepos.save(user2);



		// Comment comment1 = new Comment("hello comment 1", user1);
		// // commentRepos.save(comment1);
			
		// post1.addComment(comment1);
		// postRepos.save(post1);
		
			
		// post1.removeLikePost(user2);
		// postRepos.save(post1);

		// comment1.likeComment(user2);
		// commentRepos.save(comment1);
		
		// comment1.likeComment(admin);
		// commentRepos.save(comment1);
		
		// Tag tag1 = new Tag("kim tae hee");
		// tagRepos.save(tag1);
		
		// post1.addTagToPost(tag1);
		// postRepos.save(post1);
		
		// post1.removeTagFromPost(tag1);
		// postRepos.save(post1);
		// tagRepos.save(tag1);

		// comment1.addTagToComment(tag1);
		// commentRepos.save(comment1);

		// comment1.removeTagFromComment(tag1);
		// commentRepos.save(comment1);
		// tagRepos.save(tag1);

		// //  must save tag after remove tag from comment or post
		};
	}
}
