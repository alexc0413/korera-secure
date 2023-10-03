package com.example.korera.service;

import com.example.korera.entity.Project;
import com.example.korera.entity.Resource;
import com.example.korera.exceptions.CreationException;
import com.example.korera.exceptions.ProjectNotFoundException;
import com.example.korera.repository.FormulaRep;
import com.example.korera.repository.ProjectRep;
import com.example.korera.repository.ResourceRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProjectServiceImp implements ProjectService {
    private final ProjectRep projectRep;
    private final ResourceRep resourceRep;


    @Autowired
    public ProjectServiceImp(ProjectRep projectRep, ResourceRep resourceRep, FormulaRep formulaRep, ResourceRep resourceRep1) {
        this.projectRep = projectRep;
        this.resourceRep = resourceRep1;
    }

    @Override
    public Project createProject(Project project) {
        projectRep.save(project);
        Optional<Project> project1 = projectRep.findById(project.getProjectId());
        if(project1.isEmpty()){
            throw new CreationException("cannot create");
        }
        return project1.get();
    }

    @Override
    public void deleteProjectById(int id) {
        Project Project = getProjectById(id);
        if (Project == null) {
            throw new ProjectNotFoundException("Project is not found");
        }
        projectRep.deleteById(id);
    }

    @Override
    public Project updateProject(Project project) throws Exception {
        Integer id = project.getProjectId();
        Optional<Project> optional = projectRep.findById(id);
        if (optional.isEmpty()) {
            throw new ProjectNotFoundException("Project is not found");
        }
        Set<Resource> resource = project.getResources();
        for(Resource r : resource){
            r.getProjects().add(project);
            resourceRep.save(r);
        }

        projectRep.save(project);
        return project;
    }



    @Override
    public Project getProjectById(int id) {
        Optional<Project> Project = projectRep.findById(id);
        if (Project.isEmpty()) {
            throw new ProjectNotFoundException("Project is not found");
        }
        return Project.get();
    }

    @Override
    public List<Project> getAll() {
        return projectRep.findAll();
    }
}
