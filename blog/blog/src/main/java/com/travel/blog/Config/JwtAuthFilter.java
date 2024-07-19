package com.travel.blog.Config;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.travel.blog.Exception.JWTTokenExpired;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.net.http.HttpHeaders;

@RequiredArgsConstructor
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final UserAuthProvider userAuthProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String header  = request.getHeader("Authorization");

        if(header!=null){
            String[] authElements = header.split(" ");
            if(authElements.length ==2 && "Bearer".equals(authElements[0])) {
                try{
                    SecurityContextHolder.getContext().setAuthentication(userAuthProvider.validateToken(authElements[1]));
                }
                catch (RuntimeException e){
                    SecurityContextHolder.clearContext();
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 Unauthorized

                    throw e;
                }
            }
        }
        filterChain.doFilter(request,response);
    }
}
