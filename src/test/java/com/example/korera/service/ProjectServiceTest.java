package com.example.korera.service;

import com.example.korera.entity.Formula;
import com.example.korera.entity.Project;
import com.example.korera.repository.ProjectRep;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProjectServiceTest {

    @Autowired
    private ProjectService projectService;

    @MockBean
    private ProjectRep projectRep;

    private Project project;
    @BeforeEach
    void setUp(){
        project = Project.builder().projectId(1).projectName("first").build();
    }
    @Test
    void createProject() {
        Mockito.when(projectRep.save(project)).thenReturn(project);
        Mockito.when(projectRep.findById(project.getProjectId())).thenReturn(Optional.ofNullable(project));
        assertEquals(project,projectService.createProject(project));
    }

    @Test
    void deleteProjectById() {
        // Mockito.when(projectRep.findById(project.getProjectId())).thenReturn(Optional.ofNullable(project));
        // assertEquals(project,projectService.deleteProjectById(1));
    }

    @Test
    void updateProject() {
    }

    @Test
    void getProjectById() {
    }

    @Test
    void getAll() {
    }
}