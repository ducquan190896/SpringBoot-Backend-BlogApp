package com.quan.blogapp.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Table(name = "users")
@Entity(name = "Users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Users {
    @Id
    @SequenceGenerator(
        name = "users_sequence",
        sequenceName = "users_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "users_sequence"
    )
    @Column(name = "id", updatable = false)
    private Long id;

    @NotBlank(message = "username cannot be null")
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @NotBlank(message = "password cannot be null")
    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @JsonIgnore
    @OneToMany(mappedBy = "postOwner", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY )
    // @OneToMany(mappedBy = "postOwner")
    private List<Post> posts = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "likelist", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.DETACH, CascadeType.MERGE})
   
    private List<Post> postliked = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "commentOwner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // @OneToMany(mappedBy = "commentOwner")
    private List<Comment> comments = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "commentlikes", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REMOVE})
    private List<Comment> commentliked = new ArrayList<>();

    // @JsonIgnore
    // @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    // private List<Notification> senderNotification = new ArrayList<>();

    // @JsonIgnore
    // @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    // private List<Notification> receiverNotification = new ArrayList<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(
        name = "follower_following",
        joinColumns = {@JoinColumn(name = "following_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "followedBy_id", referencedColumnName = "id")}
    )
    private List<Users> followedsBy = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "followedsBy")
    private List<Users> followings = new ArrayList<>();

    public Users( String username,
             String password) {
        this.username = username;
        this.password = password;
    }

    public Users(String username,
             String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
    

    
}
