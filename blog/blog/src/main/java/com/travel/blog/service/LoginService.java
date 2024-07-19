package com.travel.blog.service;

import com.travel.blog.Config.UserAuthProvider;
import com.travel.blog.Dao.UserRepo;
import com.travel.blog.Exception.UserNotFoundException;
import com.travel.blog.Exception.userAlreadyExistsException;
import com.travel.blog.Mapper.UserMapper;
import com.travel.blog.Model.UserModel;
import com.travel.blog.entity.User;
import com.travel.blog.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.Optional;
import java.util.UUID;

@Service
public class LoginService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserAuthProvider userAuthProvider;

//    @Autowired
//    private

   public UserModel login(LoginRequest loginRequest){
       Optional<User> userFromDb= userRepo.findByEmailId(loginRequest.getUserEmail());
       if(userFromDb.isEmpty()){
           throw new UserNotFoundException("User Not Found", HttpStatus.NOT_FOUND);
           // userFromDb.get();
       }
       if(passwordEncoder.matches(CharBuffer.wrap(loginRequest.getPassword()),userFromDb.get().getPassWord()) ){
           return UserMapper.toDto((userFromDb.get()));
       }
       throw new UserNotFoundException("Wrong password",HttpStatus.BAD_REQUEST);

   }

   public Boolean registerNewUser(User user){
       Optional<User> userFromDb = userRepo.findByEmailId(user.getEmailId());
       if(userFromDb.isPresent()){
           throw new userAlreadyExistsException("user already exists", HttpStatus.BAD_REQUEST);
       }
       user.setUserId(UUID.randomUUID().toString());
       user.setPassWord(passwordEncoder.encode(CharBuffer.wrap(user.getPassWord())));
       var userSaved = userRepo.save(user);
       return userSaved.getId()!=null;
   }

   public boolean validateToken(String token){
       return userAuthProvider.validateToken(token).isAuthenticated();
   }
}
