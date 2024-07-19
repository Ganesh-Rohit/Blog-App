package com.travel.blog.Config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.travel.blog.Dao.UserRepo;
import com.travel.blog.Exception.UserNotFoundException;
import com.travel.blog.Mapper.UserMapper;
import com.travel.blog.Model.UserModel;
import com.travel.blog.Security.CustomUserDetailsService;
import com.travel.blog.entity.User;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class UserAuthProvider {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;


    @Value("${jwt-secret-key}")
    private String secretKey;

    @PostConstruct
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String createToken(UserModel dto){
        Date now = new Date();
        Date validity = new Date(now.getTime()+ 5*3_600_000);
        return JWT.create()
                .withIssuer(dto.getEmailId())
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .withClaim("firstName",dto.getFirstName())
                .withClaim("lastName",dto.getLastName())
                .sign(Algorithm.HMAC256(secretKey));

    }

    public Authentication validateToken(String token){
      Algorithm algorithm =  Algorithm.HMAC256(secretKey);
      JWTVerifier JWTVerifier = JWT.require(algorithm).build();
      DecodedJWT decodedJWT = JWTVerifier.verify(token);
        User user = User.builder()
                .emailId(decodedJWT.getIssuer())
                .firstName(decodedJWT.getClaim("firstName").asString())
                .lastName(decodedJWT.getClaim("lastName").asString())
                .build();
        return new UsernamePasswordAuthenticationToken(user,null, Collections.emptyList());
    }

    public Authentication validateTokenStrongly(String token){
        Algorithm algorithm =  Algorithm.HMAC256(secretKey);
        JWTVerifier JWTVerifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = JWTVerifier.verify(token);
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(decodedJWT.getIssuer());
        User user = userRepo.findByEmailId(decodedJWT.getIssuer()).orElseThrow( ()-> new UserNotFoundException("Unknown User", HttpStatus.NOT_FOUND));

        return new UsernamePasswordAuthenticationToken( UserMapper.toDto(user),null, Collections.emptyList());

    }

}
