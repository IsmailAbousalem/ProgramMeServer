package com.programme.ProgramMe.service.impl;

import com.programme.ProgramMe.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    // CRUD methods
}
