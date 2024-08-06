package com.programme.ProgramMe.service.impl;

import com.programme.ProgramMe.model.Customer;
import com.programme.ProgramMe.model.Programmer;
import com.programme.ProgramMe.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .filter(customer -> !(customer instanceof Programmer))
                .collect(Collectors.toList());
    }


    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));

//                new ResourceNotFoundException("Customer not found"));
    }


    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Long id, Customer customerDetails) {
        Customer customer = getCustomerById(id);
        customer.setEmail(customerDetails.getEmail());
        customer.setName(customerDetails.getName());
        customer.setNumber(customerDetails.getNumber());
        customer.setPassword(customerDetails.getPassword());
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        Customer customer = getCustomerById(id);
        customerRepository.delete(customer);
    }

    // CRUD methods
}
