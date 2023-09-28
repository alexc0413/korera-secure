package com.example.korera;
import com.example.korera.controller.*;
import com.example.korera.entity.Resource;
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


@WebMvcTest(ResourceController.class)
public class ResourceControllerTest {

    @MockBean
    private ResourceService resourceService;

    @Autowired
    private MockMvc mockMvc;

    private Resource resource;
    private String jsonContent;
    @BeforeEach
    public void setUp() throws JsonProcessingException {
        resource = new Resource();
        resource.setResourceId(1);
        resource.setDescription("resourceDescription");
        jsonContent = new ObjectMapper().writeValueAsString(resource);
    }

    @Test
    public void resourceCreate() throws Exception {
        when(resourceService.createResource(any(Resource.class))).thenReturn(resource);
        mockMvc.perform(post("/resource/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.resourceId",is(1)))
                .andExpect(jsonPath("$.description",is("resourceDescription")));
    }
    @Test
    public void resourceDelete() throws Exception {
        when(resourceService.deleteResourceById(1)).thenReturn(resource);
        mockMvc.perform(delete("/resource/delete/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$.resourceId",is(1)))
                .andExpect(jsonPath("$.description",is("resourceDescription")));
    }
    @Test
    public void resourceUpdate() throws Exception {
        when(resourceService.updateResource(any(Resource.class))).thenReturn(resource);
        mockMvc.perform(put("/resource/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resourceId",is(1)))
                .andExpect(jsonPath("$.description",is("resourceDescription")));
    }
    @Test
    public void resourceGetById() throws Exception {
        when(resourceService.getResourceById(1)).thenReturn(resource);
        mockMvc.perform(get("/resource/get/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resourceId",is(1)))
                .andExpect(jsonPath("$.description",is("resourceDescription")));
    }
    @Test
    public void resourceGetAll() throws Exception {
        List<Resource> list = new ArrayList<>();
        list.add(resource);
        Resource resource1 = new Resource();
        resource1.setResourceId(2);
        resource1.setDescription("resourceDescription1");
        list.add(resource1);
        when(resourceService.getAll()).thenReturn(list);

        mockMvc.perform(get("/resource/getall"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].resourceId", is(1)))
                .andExpect(jsonPath("$[0].description", is("resourceDescription")))
                .andExpect(jsonPath("$[1].resourceId", is(2)))
                .andExpect(jsonPath("$[1].description", is("resourceDescription1")));
    }
}

