package com.example.korera.service;

import com.example.korera.dto.ResponseProject;
import com.example.korera.entity.Formula;
import com.example.korera.entity.Project;
import com.example.korera.entity.Resource;
import com.example.korera.exceptions.FormulaNotFoundException;
import com.example.korera.exceptions.ProjectNotFoundException;
import com.example.korera.exceptions.ResourceNotFoundException;
import com.example.korera.repository.FormulaRep;
import com.example.korera.repository.ProjectRep;
import com.example.korera.repository.ResourceRep;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImp implements ProjectService {
    private final ProjectRep projectRep;
    private final ResourceRep resourceRep;
    private final FormulaRep formulaRep;

    @Autowired
    public ProjectServiceImp(ProjectRep projectRep, ResourceRep resourceRep, FormulaRep formulaRep) {
        this.projectRep = projectRep;
        this.resourceRep = resourceRep;
        this.formulaRep = formulaRep;
    }

    @Override
    public Project createProject(Project project) {
        projectRep.save(project);

        return projectRep.findById(project.getProjectId()).get();
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
    public Project updateProject(Project project) {
        Integer id = project.getProjectId();
        Optional<Project> optional = projectRep.findById(id);
        if (optional.isEmpty()) {
            throw new ProjectNotFoundException("Project is not found");
        }
//
//        Project dbProject = optional.get();
//
//        if(project.getProjectName()!=null){
//            dbProject.setProjectName(project.getProjectName());
//        }
//
//        //update Resources
//        if(project.getResources()!=null){
//            dbProject.getResources().clear();
//            Set<Resource> set = new HashSet<>(resourceRep.findAllById(project.getResources().stream().map(Resource::getResourceId).collect(Collectors.toList())));
//            for(Resource r:set){
//                dbProject.getResources().add(r);
//            }
//        }
//
//        //Update formula
//        if(project.getFormulas()!=null){
//           List<Formula> list = formulaRep.findAllById(project.getFormulas().stream().map(Formula::getFormulaId).collect(Collectors.toList()));
//           dbProject.getFormulas().clear();
//           for(Formula formula : list){
//               dbProject.getFormulas().add(formula);
//           }
//        }
//
//        if (project.getUser()!=null){
//            dbProject.setUser(project.getUser());
//        }

        projectRep.save(project);
        Optional<Project> optional1 = projectRep.findById(id);
        return optional1.get();
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
