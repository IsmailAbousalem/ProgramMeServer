package com.programme.ProgramMe.controller.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.programme.ProgramMe.model.Programmer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class ProgrammerControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    Programmer programmer;

    @BeforeEach
    void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        programmer = new Programmer();
        programmer.setEmail("testprogrammer@example.com");
        programmer.setName("Test Programmer");
        programmer.setNumber("0987654321");
        programmer.setPassword("password");
        programmer.setDescription("Experienced in Java");
        programmer.setSkills("Java, Spring Boot");

        // Save the programmer to the database before tests
        mockMvc.perform(post("/programmers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(programmer)));
    }

    @AfterEach
    void tearDown() throws Exception {
        // Delete the programmer from the database after tests
        mockMvc.perform(delete("/programmers/" + programmer.getId()));
    }

    @Test
    public void createProgrammer() throws Exception {
        Programmer newProgrammer = new Programmer();
        newProgrammer.setEmail("newprogrammer@example.com");
        newProgrammer.setName("New Programmer");
        newProgrammer.setNumber("1231231234");
        newProgrammer.setPassword("newpassword");
        newProgrammer.setDescription("Expert in Python");
        newProgrammer.setSkills("Python, Django");

        mockMvc.perform(post("/programmers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newProgrammer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("newprogrammer@example.com"));
    }

    @Test
    public void getProgrammerById() throws Exception {
        mockMvc.perform(get("/programmers/" + programmer.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("testprogrammer@example.com"));
    }
}
