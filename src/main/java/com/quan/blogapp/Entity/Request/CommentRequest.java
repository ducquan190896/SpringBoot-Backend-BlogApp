package com.quan.blogapp.Entity.Request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequest {
    private String content;
    private Long postId;
    private List<String> tags;
    
    public CommentRequest(String content, Long postId) {
        this.content = content;
        this.postId = postId;
    }

    
}
