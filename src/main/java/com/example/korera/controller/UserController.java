package com.example.korera.controller;

import com.example.korera.entity.User;
import com.example.korera.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User u = userService.createUser(user);
        return new ResponseEntity<>(u, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable String id){

        User u = userService.deleteUserById(id);
        return new ResponseEntity<>(u,HttpStatus.NO_CONTENT);
    }

    @PostMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        User u = userService.updateUser(user);
        return new ResponseEntity<>(u,HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id){
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<User>> getAllUser(){
        List<User> users = userService.getAll();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

}
