package com.programme.ProgramMe.repository;

import com.programme.ProgramMe.model.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setEmail("testcustomer@example.com");
        customer.setName("Test Customer");
        customer.setNumber("1234567890");
        customer.setPassword("password");

        customerRepository.save(customer);
    }

    @AfterEach
    void tearDown() {
        customerRepository.deleteById(customer.getId());
    }

    @Test
    public void findAll_customers_customerList() {
        List<Customer> customerList = customerRepository.findAll();
        assertFalse(customerList.isEmpty());
    }

    @Test
    public void findById_validId_correctCustomer() {
        Optional<Customer> customerOptional = customerRepository.findById(customer.getId());
        assertTrue(customerOptional.isPresent());
        assertEquals(customer.getEmail(), customerOptional.get().getEmail());
    }

    @Test
    public void findById_invalidId_customerNotPresent() {
        Optional<Customer> customerOptional = customerRepository.findById(999L);
        assertTrue(customerOptional.isEmpty());
    }

}