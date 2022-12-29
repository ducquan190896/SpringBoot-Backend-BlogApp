package com.quan.blogapp.Entity;

public enum NotificationType {
    PostLike("PostLike"),
    PostComment("PostComment"),
    CommentLike("CommentLike"),
    PostUnlike("PostLike"),
    CommentUnlike("CommentLike");
    private String type;

    NotificationType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

}
