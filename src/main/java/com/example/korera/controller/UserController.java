package com.example.korera.controller;

import com.example.korera.entity.User;
import com.example.korera.service.UserService;
import com.example.korera.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userServiceImp;

    @Autowired
    public UserController(UserService userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User u = userServiceImp.createUser(user);
        return new ResponseEntity<>(u, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        userServiceImp.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User u = userServiceImp.updateUser(user);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        User user = userServiceImp.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<User>> getAllUser() {
        List<User> users = userServiceImp.getAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
