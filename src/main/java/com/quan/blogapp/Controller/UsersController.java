package com.quan.blogapp.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quan.blogapp.Entity.Role;
import com.quan.blogapp.Entity.Users;
import com.quan.blogapp.Service.UsersService;

@RestController
@RequestMapping("/api/users")
public class UsersController {
    @Autowired
    UsersService usersService;

    @GetMapping("/all")
    public ResponseEntity<List<Users>> getall() {
        return new ResponseEntity<List<Users>>(usersService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Users> getUser(@PathVariable Long id) {
        return new ResponseEntity<Users>(usersService.getUser(id), HttpStatus.OK);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<Users> getUserByUsername(@PathVariable String username) {
        return new ResponseEntity<Users>(usersService.getUsersByName(username), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<HttpStatus> Register(@RequestBody Users user) {
        usersService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<Users> updateUser(@RequestBody Users user, @PathVariable Long id) {
        return new ResponseEntity<Users>(usersService.updateUsers(id, user), HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<String> testAuthentication() {
    
      
            return new ResponseEntity<String>(usersService.testAuthentication(), HttpStatus.OK);


    } 
}
