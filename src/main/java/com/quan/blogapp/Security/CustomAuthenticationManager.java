package com.quan.blogapp.Security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.quan.blogapp.Entity.Users;
import com.quan.blogapp.Repository.UsersRepos;
import com.quan.blogapp.Service.UsersService;

@Component
public class CustomAuthenticationManager implements AuthenticationManager {

    @Autowired
    UsersService usersService;
    

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Users user = usersService.getUsersByName(authentication.getName());
        if(!new BCryptPasswordEncoder().matches(authentication.getCredentials().toString(), user.getPassword())) {
            throw new BadCredentialsException("the password is wrong");
        }    
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
        return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), authorities);
    }
    
}
