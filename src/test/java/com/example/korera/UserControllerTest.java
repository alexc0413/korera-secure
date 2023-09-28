package com.example.korera;

import com.example.korera.controller.UserController;
import com.example.korera.entity.User;
import com.example.korera.repository.UserRep;
import com.example.korera.service.UserService;
import com.example.korera.util.Role;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    @MockBean
    private UserService userService;
    @Autowired
    private MockMvc mockMvc;

    private User user;
    private String jsonContent;
    @BeforeEach
    public void setUp() throws JsonProcessingException {
        user = new User();
        user.setUserName("Zeyu");
        user.setRole(Role.MEMBER);
        user.setPassword("1234");
        jsonContent = new ObjectMapper().writeValueAsString(user);
    }

    @Test
    public void userCreate() throws Exception {
        when(userService.createUser(any(User.class))).thenReturn(user);
        mockMvc.perform(post("/user/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.userName",is("Zeyu")))
                .andExpect(jsonPath("$.role",is("MEMBER")))
                .andExpect(jsonPath("$.password",is("1234")));
    }

    @Test
    public void userDelete() throws Exception{
        when(userService.deleteUserById("Zeyu")).thenReturn(user);
        mockMvc.perform(delete("/user/delete/Zeyu"))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$.userName",is("Zeyu")))
                .andExpect(jsonPath("$.role",is("MEMBER")))
                .andExpect(jsonPath("$.password",is("1234")));
    }

    @Test
    public void userUpdate() throws Exception{
        when(userService.updateUser(any(User.class))).thenReturn(user);
        mockMvc.perform(put("/user/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName",is("Zeyu")))
                .andExpect(jsonPath("$.role",is("MEMBER")))
                .andExpect(jsonPath("$.password",is("1234")));
    }

    @Test
    public void userGet() throws Exception{
        when(userService.getUserById("Zeyu")).thenReturn(user);
        mockMvc.perform(get("/user/get/Zeyu"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName",is("Zeyu")))
                .andExpect(jsonPath("$.role",is("MEMBER")))
                .andExpect(jsonPath("$.password",is("1234")));
    }

    @Test
    public void userGetAll() throws Exception{
        List<User> list =new ArrayList<>();
        list.add(user);
        User user1 = new User();
        user1.setPassword("asdf");
        user1.setUserName("Alex");
        user1.setRole(Role.MANAGEMENT);
        list.add(user1);
        when(userService.getAll()).thenReturn(list);
        mockMvc.perform(get("/user/getall"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0].password",is("1234")))
                .andExpect(jsonPath("$[0].userName",is("Zeyu")))
                .andExpect(jsonPath("$[0].role",is("MEMBER")))
                .andExpect(jsonPath("$[1].password",is("asdf")))
                .andExpect(jsonPath("$[1].userName",is("Alex")))
                .andExpect(jsonPath("$[1].role",is("MANAGEMENT")));
    }
}
