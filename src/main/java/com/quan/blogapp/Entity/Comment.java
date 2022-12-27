package com.quan.blogapp.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comment")
@Entity(name = "Comment")
public class Comment {

    @Id
    @SequenceGenerator(
        name = "comment_sequence",
        sequenceName = "comment_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "comment_sequence"
    )
    @Column(name = "id", updatable = false)
    private Long id;

    @NotBlank(message = "content cannot be blank")
    @Column(name = "content", nullable = false)
    private String content;

    @Min(value = 0, message = "like count must be higher than 0")
    @Column(name = "likecount", nullable = false)
    private Integer likecount;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE, CascadeType.DETACH, CascadeType.MERGE})
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private Users commentOwner;


    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.DETACH, CascadeType.MERGE})
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Post post;


    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REMOVE})
    @JoinTable(
        name = "comment_likes",
        joinColumns = {@JoinColumn(name = "comment_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name= "liked_user_id", referencedColumnName = "id")}
    )
    private List<Users> commentlikes = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REMOVE})
    @JoinTable(
        name = "comment_tags",
        joinColumns = {@JoinColumn(name= "comment_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "tag_id", referencedColumnName = "id")}
    )
    private List<Tag> tagList = new ArrayList<>();


    public Comment( String content, Users commentOwner, Post post) {
        this.content = content;
        this.commentOwner = commentOwner;
        this.likecount = 0;
        this.post = post;
    }
    public Comment( String content, Users commentOwner) {
        this.content = content;
        this.commentOwner = commentOwner;
        this.likecount = 0;
      
    }
    @Override
    public int hashCode() {
       return Objects.hash(this.id);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
      
        if(!(obj instanceof Comment)) {
            return false;
        }
        Comment other = (Comment) obj;

       
        return Objects.equals(this.id, other.getId());
    }

    public void likeComment(Users user) {
        this.commentlikes.add(user);
        this.setLikecount(this.likecount + 1);
    }
    public void removeLikeComment(Users user) {
      this.commentlikes =  this.commentlikes.stream().filter(us -> us.getId() != user.getId()).collect(Collectors.toList());
      this.setLikecount(this.likecount - 1);

    }

    public void addTagToComment(Tag tag) {
        this.tagList.add(tag);
        tag.getCommentList().add(this);
        tag.setTagcounter(tag.getTagcounter() + 1);
    }

    public void removeTagFromComment(Tag tag) {
        this.tagList.remove(tag);
        tag.getCommentList().remove(this);
        tag.setTagcounter(tag.getTagcounter() - 1);
    }

    


}
