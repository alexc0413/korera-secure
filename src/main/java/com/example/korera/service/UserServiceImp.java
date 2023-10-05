package com.example.korera.service;

import com.example.korera.entity.Project;
import com.example.korera.entity.User;
import com.example.korera.exceptions.CreationException;
import com.example.korera.exceptions.UserNotFoundException;
import com.example.korera.repository.ProjectRep;
import com.example.korera.repository.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    private final UserRep userRep;
    private final ProjectRep projectRep;

    @Autowired
    public UserServiceImp(UserRep userRep, ProjectRep projectRep) {
        this.userRep = userRep;
        this.projectRep = projectRep;
    }

    @Override
    public User createUser(User user) {
        userRep.save(user);
        Optional<User> user1 = userRep.findById(user.getUsername());
        if(user1.isEmpty()){
            throw new CreationException("cannot create");
        }
        return user1.get();
    }

    @Override
    public void deleteUserById(String id) {
        User user = getUserById(id);
        if (user == null) {
            throw new UserNotFoundException("User it not found");
        }
        userRep.deleteById(id);
    }

    @Override
    public User updateUser(User user) {
        String id = user.getUsername();
        User u = getUserById(id);
        if (u == null) {
            throw new UserNotFoundException("User it not found");
        }
        List<Project> projects = user.getProjects();
        for(Project p : projects){
            p.setUser(user);
//            projectRep.save(p);
        }

        userRep.save(user);
        return user;
    }

    @Override
    public User getUserById(String id) {
        Optional<User> user = userRep.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User it not found");
        }
        return user.get();
    }

    @Override
    public List<User> getAll() {
        return userRep.findAll();
    }
}
