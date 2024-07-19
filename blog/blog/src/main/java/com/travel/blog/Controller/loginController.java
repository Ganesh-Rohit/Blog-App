package com.travel.blog.Controller;

import com.travel.blog.Config.UserAuthProvider;
import com.travel.blog.Model.UserModel;
import com.travel.blog.entity.User;
import com.travel.blog.request.LoginRequest;
import com.travel.blog.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/auth")
@RestController
public class loginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserAuthProvider userAuthProvider;

    @PostMapping("/login")
    public UserModel login(@RequestBody LoginRequest loginRequest){
        UserModel user=  loginService.login(loginRequest);
        user.setToken(userAuthProvider.createToken(user));
        return user;
    }

    @PostMapping("/register")
    public ResponseEntity<Boolean> registerNewUser(@RequestBody User user){
        return ResponseEntity.ok(loginService.registerNewUser(user));
    }

    @PostMapping("/token/validity")
    public boolean validateToken(@RequestParam String token){
        return loginService.validateToken(token);
    }
}
