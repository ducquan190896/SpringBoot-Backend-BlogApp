package com.quan.blogapp.Entity.Request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostRequest {
    private String content;
    private String imageurl;
    private List<String> tags;
    @Override
    public String toString() {
        return "PostRequest [content=" + content + ", imageurl=" + imageurl + ", tags=" + tags + "]";
    }
    public PostRequest(String content, String imageurl) {
        this.content = content;
        this.imageurl = imageurl;
    }

    
}
