package com.travel.blog.Config;

import com.travel.blog.Exception.JWTTokenExpired;
import com.travel.blog.Exception.UserNotFoundException;
import com.travel.blog.Exception.userAlreadyExistsException;
import com.travel.blog.Model.ErrorPojo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = {UserNotFoundException.class})
    @ResponseBody
    public ResponseEntity<ErrorPojo> handleException(UserNotFoundException ex){
        return ResponseEntity.status(ex.getHttpStatus()).body(new ErrorPojo(ex.getMessage(),ex.getHttpStatus()));
    }

    @ExceptionHandler(value = {userAlreadyExistsException.class})
    @ResponseBody
    public ResponseEntity<ErrorPojo> handleUserAlreadyExistsException(userAlreadyExistsException ex){
        return ResponseEntity.status(ex.getHttpStatus()).body(new ErrorPojo(ex.getMessage(),ex.getHttpStatus()));
    }

    @ExceptionHandler(value = {JWTTokenExpired.class})
    @ResponseBody
    public ResponseEntity<ErrorPojo> handleTokenExpiryException(JWTTokenExpired ex){
        return ResponseEntity.status(ex.getHttpStatus()).body(new ErrorPojo(ex.getMessage(),ex.getHttpStatus()));
    }
}
