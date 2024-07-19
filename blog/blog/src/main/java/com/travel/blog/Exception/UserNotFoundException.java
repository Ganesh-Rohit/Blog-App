package com.travel.blog.Exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class UserNotFoundException extends RuntimeException{

    private HttpStatus httpStatus;
    public UserNotFoundException(String msg, HttpStatus httpStatus){
        super(msg);
        this.httpStatus = httpStatus;
    }
}
