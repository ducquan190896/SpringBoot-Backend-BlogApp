package com.quan.blogapp.Service;

import java.util.List;

import com.quan.blogapp.Entity.Users;

public interface UsersService {
    List<Users> getUsers();
    Users getUser(Long id);
    Users getUsersByName(String username);
    void saveUser(Users user);
    Users updateUsers(Long id, Users user);
    void deleteUsers(Long id);
    String testAuthentication();
}
