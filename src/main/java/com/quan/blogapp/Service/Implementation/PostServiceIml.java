package com.quan.blogapp.Service.Implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.quan.blogapp.Entity.Comment;
import com.quan.blogapp.Entity.Notification;
import com.quan.blogapp.Entity.NotificationType;
import com.quan.blogapp.Entity.Post;
import com.quan.blogapp.Entity.Tag;
import com.quan.blogapp.Entity.Users;
import com.quan.blogapp.Entity.Request.CommentRequest;
import com.quan.blogapp.Entity.Request.PostRequest;
import com.quan.blogapp.Exception.EntityNotFoundException;
import com.quan.blogapp.Repository.PostRepos;
import com.quan.blogapp.Repository.TagRepos;
import com.quan.blogapp.Repository.UsersRepos;
import com.quan.blogapp.Service.NotificationService;
import com.quan.blogapp.Service.PostService;
@Service
public class PostServiceIml implements PostService {
    @Autowired
    PostRepos postRepos;
    @Autowired
    UsersRepos usersRepos;
    @Autowired
    NotificationService notificationService;
    @Autowired
    TagRepos tagRepos;
    

    @Override
    public void createPost(PostRequest postRequest) {
        
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Users> entity = usersRepos.findByUsername(username);
        if(!entity.isPresent()) {
            throw new EntityNotFoundException("the username " + username + " not found");

        } 
        Users user = entity.get();
        Post post = new Post(postRequest.getContent(), postRequest.getImageurl(), user);
        if(postRequest.getTags().size() > 0) {
            postRequest.getTags().stream().forEach(tagname ->{
                Optional<Tag> tagEntity = tagRepos.findByContent(tagname);
                if(tagEntity.isPresent()) {
                    post.addTagToPost(tagEntity.get());
                } else {
                    post.addTagToPost(new Tag(tagname));
                }
              
            });
        }
        
        postRepos.save(post);
        System.out.println(post);
    }

    @Override
    public void deletePost(Long id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Users> entityAuth = usersRepos.findByUsername(username);
        if(!entityAuth.isPresent()) {
            throw new EntityNotFoundException("the username " + username + " not found");

        } 
        Users authUser = entityAuth.get();

        Optional<Post> entity = postRepos.findById(id);
        if(!entity.isPresent()) {
            throw new EntityNotFoundException("the post with id " + id + " not found");
        }
        Post post = entity.get();

        if(!post.getPostOwner().equals(authUser)) {
            throw new EntityNotFoundException("you cannot delete the post because you are not the post owner");
        }
        
        postRepos.delete(post);;
        
    }

    @Override
    public Post getPost(Long id) {
        Optional<Post> entity = postRepos.findById(id);
        if(!entity.isPresent()) {
            throw new EntityNotFoundException("the post with id " + id + " not found");
        }
        return entity.get();
    }

    @Override
    public List<Post> getPosts() {
       return postRepos.findAll();
    }

    @Override
    public List<Post> getPostsByUsername(String username) {
        Optional<Users> entity = usersRepos.findByUsername(username);
        if(!entity.isPresent()) {
            throw new EntityNotFoundException("the username " + username + " not found");

        } 
        Users user = entity.get();
        return postRepos.findPostByPostOwner(user);
    }

    @Override
    public Notification likePost(Long postId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Users> entity = usersRepos.findByUsername(username);
        if(!entity.isPresent()) {
            throw new EntityNotFoundException("the username " + username + " not found");

        } 
        Users user = entity.get();

        Post post = getPost(postId);
        if(post.getLikelist().contains(user)) {
            throw new EntityNotFoundException("the current user already liked the post");
        }
        post.likePost(user);
        postRepos.save(post);
        return notificationService.sendNotification(NotificationType.PostLike, user, user, post);
    }

    @Override
    public void removeLikePost(Long postId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Users> entity = usersRepos.findByUsername(username);
        if(!entity.isPresent()) {
            throw new EntityNotFoundException("the username " + username + " not found");

        } 
        Users user = entity.get();

        Post post = getPost(postId);
        if(!post.getLikelist().contains(user)) {
            throw new EntityNotFoundException("the current user has not liked the post, cannot remove the like");
        }
        post.removeLikePost(user);
        postRepos.save(post);
        
    }

    @Override
    public List<Users> getLikeList(Long postId) {
        Post post = getPost(postId);
        return post.getLikelist();
    }

  

   

    @Override
    public Notification addComment(CommentRequest commentRequest) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Users> entity = usersRepos.findByUsername(username);
        if(!entity.isPresent()) {
            throw new EntityNotFoundException("the username " + username + " not found");

        } 
        Users user = entity.get();
        Post post = getPost(commentRequest.getPostId());
       Comment comment = new Comment(commentRequest.getContent(), user, post);

       if(commentRequest.getTags().size() > 0) {
            commentRequest.getTags().stream().forEach(tagname -> {
                Optional<Tag> tag = tagRepos.findByContent(tagname);
                if(tag.isPresent()) {
                    comment.addTagToComment(tag.get());
                } else {
                    comment.addTagToComment(new Tag(tagname));
                }
            });
       }
       post.addComment(comment);
       postRepos.save(post);

       if(post.getPostOwner().equals(user)) {
        return null;
       }

       return notificationService.sendNotification(NotificationType.PostComment, user, post.getPostOwner(), post);

    }

   
 

    
}
