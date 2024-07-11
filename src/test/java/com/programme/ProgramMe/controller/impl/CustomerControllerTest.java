package com.programme.ProgramMe.controller.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.programme.ProgramMe.model.Customer;
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
public class CustomerControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    Customer customer;

    @BeforeEach
    void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        customer = new Customer();
        customer.setEmail("testcustomer@example.com");
        customer.setName("Test Customer");
        customer.setNumber("1234567890");
        customer.setPassword("password");

        // Save the customer to the database before tests and retrieve the saved customer to get the ID
        String response = mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Customer savedCustomer = objectMapper.readValue(response, Customer.class);
        customer.setId(savedCustomer.getId()); // Ensure the customer object has the ID set for further tests
    }

    @AfterEach
    void tearDown() throws Exception {
        // Delete the customer from the database after tests
        mockMvc.perform(delete("/customers/" + customer.getId()));
    }

    @Test
    public void createCustomer() throws Exception {
        Customer newCustomer = new Customer();
        newCustomer.setEmail("newcustomer@example.com");
        newCustomer.setName("New Customer");
        newCustomer.setNumber("1231231234");
        newCustomer.setPassword("newpassword");

        mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newCustomer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("newcustomer@example.com"));
    }

    @Test
    public void getCustomerById() throws Exception {
        mockMvc.perform(get("/customers/" + customer.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("testcustomer@example.com"));
    }
}
