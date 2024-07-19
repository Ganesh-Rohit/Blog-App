package com.travel.blog.Model;

import org.springframework.http.HttpStatus;

public class ErrorPojo {
    private String message;
    private HttpStatus status;

    public ErrorPojo(String message,HttpStatus status){
        this.message = message;
        this.status = status;
    }
}
