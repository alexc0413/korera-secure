package com.example.korera.controller;


import com.example.korera.entity.User;
import com.example.korera.repository.UserRep;
import com.example.korera.util.Role;
import org.hibernate.id.uuid.UuidGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/")
public class DemoController {

    @Autowired
    UserRep userRep;
    @GetMapping("/save")
    public void saveUser(){
        User u = new User("Zeyu");
        u.setRole(Role.MANAGEMENT);
        u.setCreateDate(LocalDateTime.now());
        u.setLastModify(LocalDateTime.now());
        u.setPassword("asdf");

        User u1 = new User("Alex");
        u1.setRole(Role.MANAGEMENT);
        u1.setCreateDate(LocalDateTime.now());
        u1.setLastModify(LocalDateTime.now());
        u1.setPassword("qwer");
        userRep.save(u1);
        userRep.save(u);
    }

    @GetMapping("/getUser")
    public User getUser(){
        String name = "Zeyu";
        String value =  UUID.nameUUIDFromBytes(name.getBytes()).toString();
        return userRep.findById(value).orElse(null);
    }

    @GetMapping("/getAll")
    public List<User> test(){
        return userRep.findAll();
    }

    @DeleteMapping("/delete")
    public void delete(){
        String name = "Zeyu";
        String value =  UUID.nameUUIDFromBytes(name.getBytes()).toString();
        userRep.deleteById(value);
    }

    @PutMapping("/update/{id}")
    public User update(@PathVariable String id){
        String value =  UUID.nameUUIDFromBytes(id.getBytes()).toString();
        User user = userRep.findById(value).orElse(null);
        assert user != null;
        user.setPassword("12345");
        user.setRole(Role.MEMBER);
        return userRep.save(user);
    }
}
