package com.quan.blogapp.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tag")
@Entity(name = "Tag")
public class Tag {
    @Id
    @SequenceGenerator(
        name = "tag_sequence",
        sequenceName =  "tag_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "tag_sequence"
    )
    private Long id;

    @NotBlank(message = "content cannot be blank")
    @Column(name = "content", nullable = false, unique = true)
    private String content;

    @Min(value = 0, message = "tag counter must be higher than 0")
    @Column(name = "tagcounter", nullable = false)
    private Integer tagcounter;

    @JsonIgnore
    @ManyToMany(mappedBy = "tagList", cascade = {CascadeType.REFRESH, CascadeType.REMOVE, CascadeType.DETACH, CascadeType.MERGE})
    private List<Post> postList = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "tagList", cascade = {CascadeType.REFRESH, CascadeType.REMOVE, CascadeType.DETACH, CascadeType.MERGE})
    private List<Comment> commentList = new ArrayList<>();
    
    @Override
    public int hashCode() {
      return Objects.hash(this.id, this.content);
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Tag)) 
            return false;
        Tag other = (Tag) obj;
        
        return Objects.equals(this.id, other.getId()) && Objects.equals(this.content, other.getContent());
    }


    @Override
    public String toString() {
        return "Tag [id=" + id + ", content=" + content + ", tagcounter=" + tagcounter + "]";
    }


    public Tag(String content) {
        this.content = content;
        this.tagcounter = 0;
    }
    
    
}
