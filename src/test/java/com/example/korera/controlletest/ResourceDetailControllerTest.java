package com.example.korera.controlletest;


import com.example.korera.controller.ResourceDetailController;
import com.example.korera.entity.ResourceDetail;
import com.example.korera.service.ResourceDetailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;
@WebMvcTest(ResourceDetailController.class)
public class ResourceDetailControllerTest {
    @MockBean
    private ResourceDetailService resourceDetailService;

    @Autowired
    private MockMvc mockMvc;

    private ResourceDetail resourceDetail;
    private String jsonContent;

    @BeforeEach
    public void setUp() throws JsonProcessingException {
        resourceDetail = new ResourceDetail();
        resourceDetail.setId(1);
        resourceDetail.setDetail("First");
        jsonContent = new ObjectMapper().writeValueAsString(resourceDetail);
    }

    @Test
    public void resourceDetailCreate() throws Exception {
        when(resourceDetailService.createResourceDetail(any(ResourceDetail.class))).thenReturn(resourceDetail);
        mockMvc.perform(post("/resourcedetail/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.detail",is("First")));
    }

    @Test
    public void resourceDetailDelete() throws Exception {
        when(resourceDetailService.deleteResourceDetailById(1)).thenReturn(resourceDetail);
        mockMvc.perform(delete("/resourcedetail/delete/1"))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.detail",is("First")));
    }

    @Test
    public void resourceDetailPut() throws Exception {
        when(resourceDetailService.updateResourceDetail(any(ResourceDetail.class))).thenReturn(resourceDetail);
        mockMvc.perform(put("/resourcedetail/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.detail",is("First")));
    }

    @Test
    public void resourceDetailGet() throws Exception{
        when(resourceDetailService.getResourceDetailById(1)).thenReturn(resourceDetail);
        mockMvc.perform(get("/resourcedetail/get/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.detail",is("First")));
    }

    @Test
    public void resourceGetAll() throws Exception {
        List<ResourceDetail> list = new ArrayList<>();
        list.add(resourceDetail);
        ResourceDetail resourceDetail1 = new ResourceDetail();
        resourceDetail1.setId(2);
        resourceDetail1.setDetail("second");
        list.add(resourceDetail1);
        when(resourceDetailService.getAll()).thenReturn(list);
        mockMvc.perform(get("/resourcedetail/getall"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0].id",is(1)))
                .andExpect(jsonPath("$[0].detail",is("First")))
                .andExpect(jsonPath("$[1].id",is(2)))
                .andExpect(jsonPath("$[1].detail",is("second")));
    }

}
