package com.example.korera.controller;

import com.example.korera.entity.Project;
import com.example.korera.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService projectService;
    @Autowired
    public ProjectController(ProjectService projectService){
        this.projectService = projectService;
    }

    @PostMapping("/create")
    public ResponseEntity<Project> createProject(@RequestBody Project project){
        Project p = projectService.createProject(project);
        return new ResponseEntity<>(p, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Project> deleteProject(@PathVariable int id){

        Project p = projectService.deleteProjectById(id);
        return new ResponseEntity<>(p,HttpStatus.NO_CONTENT);
    }

    @PostMapping("/update")
    public ResponseEntity<Project> updateProject(@RequestBody Project project){
        Project p = projectService.updateProject(project);
        return new ResponseEntity<>(p,HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable int id){
        Project p = projectService.getProjectById(id);
        return new ResponseEntity<>(p,HttpStatus.OK);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Project>> getAllProject(){
        List<Project> projects = projectService.getAll();
        return new ResponseEntity<>(projects,HttpStatus.OK);
    }

}
