package com.example.korera.service;

import com.example.korera.entity.Project;
import com.example.korera.exceptions.ProjectNotFoundException;
import com.example.korera.repository.ProjectRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImp implements ProjectService {
    private final ProjectRep projectRep;

    @Autowired
    public ProjectServiceImp(ProjectRep projectRep){
        this.projectRep = projectRep;
    }

    @Override
    public Project createProject(Project project){
        projectRep.save(project);
        return project;
    }

    @Override
    public Project deleteProjectById(int id){
        Project Project = getProjectById(id);
        if(Project==null){
            throw new ProjectNotFoundException("Project is not found");
        }
        projectRep.deleteById(id);
        return Project;
    }

    @Override
    public Project updateProject(Project project){
        if(project==null){
            throw new ProjectNotFoundException("Project is not found");
        }
        projectRep.save(project);
        return project;
    }

    @Override
    public Project getProjectById(int id){
        Optional<Project> Project = projectRep.findById(id);
        if(Project.isEmpty()){
            throw new ProjectNotFoundException("Project is not found");
        }
        return Project.get();
    }

    @Override
    public List<Project> getAll(){
        return projectRep.findAll();
    }
}
