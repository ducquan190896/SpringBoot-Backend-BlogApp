package com.quan.blogapp.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "post")
@Entity(name = "Post")
public class Post {
    @Id
    @SequenceGenerator(
        name = "post_sequence",
        sequenceName =  "post_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "post_sequence"
    )
    @Column(name = "id", updatable = false)
    private Long id;

    @NotBlank(message = "content cannot be blank")
    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "imageurl")
    private String imageurl;

    @Min(value = 0, message = "comment count must be higher than 0")
    @Column(name = "commentcount", nullable = false)
    private Integer commentcount;

    @Min(value = 0, message = "like count must be higher than 0")
    @Column(name = "likecount", nullable = false)
    private Integer likecount;

   
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "postowner_id", referencedColumnName = "id")
    private Users postOwner;

    @JsonIgnore
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch =  FetchType.LAZY)
    // @OneToMany
    private List<Comment> comments = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.DETACH, CascadeType.MERGE})
    @JoinTable(
        name = "post_likes",
        joinColumns = @JoinColumn(name = "post_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "liked_user_id", referencedColumnName = "id")     
       
    )
    private List<Users> likelist = new ArrayList<>();

    // @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.REMOVE, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
        name = "post_tags",
        joinColumns = {@JoinColumn(name = "post_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "tag_id", referencedColumnName = "id")}
    )
    private List<Tag> tagList = new ArrayList<>();

    // @JsonIgnore
    // @OneToMany(mappedBy = "postNotify", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    // private List<Notification> listnotification = new ArrayList<>();

    public Post( String content, String imageurl, Users postOwner) {
        this.content = content;
        this.imageurl = imageurl;
        this.postOwner = postOwner;
        this.likecount = 0;
        this.commentcount = 0;
    }
    public Post( String content, String imageurl, Users postOwner, List<Tag> tags) {
        this.content = content;
        this.imageurl = imageurl;
        this.postOwner = postOwner;
        this.likecount = 0;
        this.commentcount = 0;
        this.tagList = tags;
    }
 

    public Post( String content, String imageurl) {
        this.content = content;
        this.imageurl = imageurl;
        this.likecount = 0;
        this.commentcount = 0;
    }

    public void likePost(Users user) {
        this.likelist.add(user);
        this.likecount = this.likecount +1;
        user.getPostliked().add(this);
       
    }
    public void removeLikePost(Users user) {
        if(this.likelist.contains(user)) {
            this.likelist.remove(user);
            this.likecount = this.likecount - 1;
        }
      
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
       
        this.commentcount = this.commentcount + 1;
        comment.setPost(this);
    }

    public void deleteComment(Comment comment) {
      this.comments =  this.comments.stream().filter(comm -> comm.getId() != comment.getId()).collect(Collectors.toList());
    this.commentcount = this.commentcount - 1;
        comment.setPost(null);
    }

    public void addTagToPost(Tag tag) {
        this.tagList.add(tag);
        tag.setTagcounter(tag.getTagcounter() + 1);
        tag.getPostList().add(this);
    }

    public void removeTagFromPost(Tag tag) {
        this.tagList = this.tagList.stream().filter(ta -> ta.getId() != tag.getId()).collect(Collectors.toList());
        tag.setTagcounter(tag.getTagcounter() - 1);
        tag.getPostList().remove(this);
    }



    @Override
    public int hashCode() {
       return Objects.hash(id, postOwner);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if(!(obj instanceof Post) || obj == null) {
            return false;
        }
       

        Post post = (Post) obj;
       
        return Objects.equals(id, post.getId()) && Objects.equals(postOwner, post.getPostOwner());
    }
    @Override
    public String toString() {
        return "Post [id=" + id + ", content=" + content + ", imageurl=" + imageurl + ", commentcount=" + commentcount
                + ", likecount=" + likecount + ", postOwner=" + postOwner + ", tagList=" + tagList + "]";
    }
    
    
    
}
