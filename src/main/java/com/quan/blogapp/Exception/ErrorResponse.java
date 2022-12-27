package com.quan.blogapp.Exception;

import java.time.LocalDateTime;

public class ErrorResponse {
    private String message;
    private Throwable throwable;
    private LocalDateTime localDateTime;

    public ErrorResponse(String message, Throwable throwable, LocalDateTime localDateTime) {
        this.message = message;
        this.throwable = throwable;
        this.localDateTime = localDateTime;
    }
}
