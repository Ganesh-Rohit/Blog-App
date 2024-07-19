package com.travel.blog.Exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class userAlreadyExistsException extends RuntimeException{

    private HttpStatus httpStatus;
    public userAlreadyExistsException(String msg, HttpStatus httpStatus){
        super(msg);
        this.httpStatus = httpStatus;
    }
}
