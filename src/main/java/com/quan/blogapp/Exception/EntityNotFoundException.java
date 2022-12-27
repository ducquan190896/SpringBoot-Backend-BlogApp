package com.quan.blogapp.Exception;

public class EntityNotFoundException extends RuntimeException {
    private String message;
    public EntityNotFoundException() {
        super();

    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}
