package com.quan.blogapp.Security.Filter;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.quan.blogapp.Security.SecurityConstant;

public class FilterJwtAuthorization extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        if(header == null || !header.startsWith(SecurityConstant.authorization)) {
            filterChain.doFilter(request, response);
            return;
        }
         String token = header.replace( SecurityConstant.authorization, "");
         DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(SecurityConstant.private_key)).build().verify(token);
         String username = decodedJWT.getSubject();
         Date expirytoken = decodedJWT.getExpiresAt();
         List<String> claims= decodedJWT.getClaim("authorities").asList(String.class);
         List<SimpleGrantedAuthority> authorities = claims.stream().map(cla -> new SimpleGrantedAuthority(cla.toString())).collect(Collectors.toList());
        Date dateNow =  new Date(System.currentTimeMillis());

         if(expirytoken.compareTo(dateNow) < 0) {
            throw new RuntimeException("the token expries");
         }
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
    
}
