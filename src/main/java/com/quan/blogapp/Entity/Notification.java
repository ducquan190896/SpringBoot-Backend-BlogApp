package com.quan.blogapp.Entity;
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
@Table(name = "notification")
@Entity(name = "Notification")
public class Notification {
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
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private NotificationType type;
 
    @Column(name = "isseen", nullable = false)
    private boolean isseen;

    
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.DETACH})
    @JoinColumn(name = "sender_id", referencedColumnName = "id")
    private Users sender;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.DETACH})
    @JoinColumn(name = "reciever_id", referencedColumnName = "id")
    private Users receiver;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.DETACH})
    @JoinColumn(name = "post_notify_id", referencedColumnName = "id")
    private Post postNotify;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.DETACH})
    @JoinColumn(name = "comment_notify_id", referencedColumnName = "id")
    private Comment commentNotify;


    

    
    public Notification(NotificationType type, Users sender, Users receiver, Post postNotify) {
        this.type = type;
        this.sender = sender;
        this.receiver = receiver;
        this.postNotify = postNotify;
        this.isseen = false;
    }

    


    public Notification(NotificationType type, Users sender, Users receiver, Comment commentNotify) {
        this.type = type;
        this.sender = sender;
        this.receiver = receiver;
        this.commentNotify = commentNotify;
        this.isseen = false;
    }




    @Override
    public int hashCode() {
        return Objects.hash(this.sender, this.receiver, this.postNotify, this.commentNotify, this.id);
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Notification other = (Notification) obj;
        return Objects.equals(this.id, other.getId()) && Objects.equals(this.sender, other.getSender()) &&  Objects.equals(this.receiver, other.getReceiver()) && (Objects.equals(this.postNotify, other.getPostNotify()) || Objects.equals(this.commentNotify, other.getCommentNotify()));
    }




    @Override
    public String toString() {
        return "Notification [id=" + id + ", type=" + type + ", isseen=" + isseen + ", sender=" + sender + ", receiver="
                + receiver + ", postNotify=" + postNotify + ", commentNotify=" + commentNotify + "]";
    }

    
}
