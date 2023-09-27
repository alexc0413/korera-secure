package com.example.korera.service;

import com.example.korera.entity.User;

import java.util.List;

public interface UserService {
    User createUser(User user);

    User deleteUserById(String id);

    User updateUser(User user);

    User getUserById(String id);

    List<User> getAll();
}
