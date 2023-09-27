package com.example.korera.service;

import com.example.korera.entity.User;
import com.example.korera.exceptions.UserNotFoundException;
import com.example.korera.repository.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    private final UserRep userRep;

    @Autowired
    public UserServiceImp(UserRep userRep){
        this.userRep = userRep;
    }

    @Override
    public User createUser(User user){
        userRep.save(user);
        return user;
    }

    @Override
    public User deleteUserById(String id){
        User user = getUserById(id);
        if(user==null){
            throw new UserNotFoundException("User it not found");
        }
        userRep.deleteById(id);
        return user;
    }

    @Override
    public User updateUser(User user){
        String id = user.getUserName();
        User u = getUserById(id);
        if(u==null){
            throw new UserNotFoundException("User it not found");
        }
        userRep.save(user);
        return user;
    }

    @Override
    public User getUserById(String id){
        Optional<User> user = userRep.findById(id);
        if(user.isEmpty()){
            throw new UserNotFoundException("User it not found");
        }
        return user.get();
    }

    @Override
    public List<User> getAll(){
        return userRep.findAll();
    }
}
