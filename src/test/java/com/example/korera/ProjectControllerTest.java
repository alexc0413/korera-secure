package com.example.korera;
import com.example.korera.controller.*;
import com.example.korera.entity.Project;
import com.example.korera.service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;


@WebMvcTest(ProjectController.class)
public class ProjectControllerTest {

    @MockBean
    private ProjectService projectService;

    @Autowired
    private MockMvc mockMvc;

    private Project project;
    private String jsonContent;
    @BeforeEach
    public void setUp() throws JsonProcessingException {
        project = new Project();
        project.setProjectId(1);
        project.setProjectName("project");
        jsonContent = new ObjectMapper().writeValueAsString(project);
    }

    @Test
    public void projectCreate() throws Exception {
        when(projectService.createProject(any(Project.class))).thenReturn(project);
        mockMvc.perform(post("/project/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.projectId",is(1)))
                .andExpect(jsonPath("$.projectName",is("project")));
    }
    @Test
    public void projectDelete() throws Exception {
        when(projectService.deleteProjectById(1)).thenReturn(project);
        mockMvc.perform(delete("/project/delete/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$.projectId",is(1)))
                .andExpect(jsonPath("$.projectName",is("project")));
    }
    @Test
    public void projectUpdate() throws Exception {
        when(projectService.updateProject(any(Project.class))).thenReturn(project);
        mockMvc.perform(put("/project/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.projectId",is(1)))
                .andExpect(jsonPath("$.projectName",is("project")));
    }
    @Test
    public void projectGetById() throws Exception {
        when(projectService.getProjectById(1)).thenReturn(project);
        mockMvc.perform(get("/project/get/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.projectId",is(1)))
                .andExpect(jsonPath("$.projectName",is("project")));
    }
    @Test
    public void projectGetAll() throws Exception {
        List<Project> list = new ArrayList<>();
        list.add(project);
        Project project1 = new Project();
        project1.setProjectId(2);
        project1.setProjectName("this is project");
        list.add(project1);
        when(projectService.getAll()).thenReturn(list);

        mockMvc.perform(get("/project/getall"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].projectId", is(1)))
                .andExpect(jsonPath("$[0].projectName", is("project")))
                .andExpect(jsonPath("$[1].projectId", is(2)))
                .andExpect(jsonPath("$[1].projectName", is("this is project")));
    }
}
