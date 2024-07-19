package com.travel.blog.Exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class JWTTokenExpired extends RuntimeException{

    private HttpStatus httpStatus;
    public JWTTokenExpired(String msg, HttpStatus httpStatus){
        super(msg);
        this.httpStatus = httpStatus;
    }
}
