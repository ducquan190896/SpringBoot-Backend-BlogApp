package com.quan.blogapp.Service.Implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.quan.blogapp.Entity.Role;
import com.quan.blogapp.Entity.Users;
import com.quan.blogapp.Repository.UsersRepos;
import com.quan.blogapp.Service.UsersService;

@Service
public class UsersServiceIml implements UsersService, UserDetailsService {
    @Autowired
    UsersRepos usersRepos;

    @Override
    public void deleteUsers(Long id) {
        Optional<Users> entity = usersRepos.findById(id);
        Users user = isCheck(entity, id);
        usersRepos.delete(user);
        
    }

    @Override
    public String testAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @Override
    public List<Users> getUsers() {
        return usersRepos.findAll();
    }

    @Override
    public Users getUser(Long id) {
        Optional<Users> entity = usersRepos.findById(id);
        Users user = isCheck(entity, id);
      return user;  
    }

    @Override
    public Users getUsersByName(String username) {
        Optional<Users> entity = usersRepos.findByUsername(username);
        Users user = isCheck(entity, 404L);
        return user;
    }

    @Override
    public void saveUser(Users user) {
        Optional<Users> entity = usersRepos.findByUsername(user.getUsername());
        
        if(entity.isPresent()) {
            throw new EntityNotFoundException("the username " + user.getUsername() + " is already exist");
        }
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setRole(Role.USER);
        usersRepos.save(user);
        
    }

    @Override
    public Users updateUsers(Long id, Users user) {
        Optional<Users> entity = usersRepos.findById(id);
        Users user1 = isCheck(entity, id);
        user1.setPassword(user.getPassword());
        return usersRepos.save(user1);
    }

    private Users isCheck(Optional<Users> entity, Long id) {
        if(entity.isPresent()) {
            return entity.get();
        }
        throw new EntityNotFoundException("the user with id " + id + " not found");
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user1 = getUsersByName(username);
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user1.getRole().getName()));
        User user = new User(user1.getUsername(), user1.getPassword(), authorities);
        return user;
    }
    
}
