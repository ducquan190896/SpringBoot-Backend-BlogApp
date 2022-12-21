package com.quan.blogapp.Service.Implementation;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quan.blogapp.Entity.Users;
import com.quan.blogapp.Repository.UsersRepos;
import com.quan.blogapp.Service.UsersService;

@Service
public class UsersServiceIml implements UsersService {
    @Autowired
    UsersRepos usersRepos;

    @Override
    public void deleteUsers(Long id) {
        Optional<Users> entity = usersRepos.findById(id);
        Users user = isCheck(entity, id);
        usersRepos.delete(user);
        
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
    
}
