package com.programme.ProgramMe.service.impl;

import com.programme.ProgramMe.model.Customer;
import com.programme.ProgramMe.model.Programmer;
import com.programme.ProgramMe.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .filter(customer -> !(customer instanceof Programmer))
                .collect(Collectors.toList());
    }


    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
    }


    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Long id, Customer customerDetails) {
        Customer customer = getCustomerById(id);

        if (customerDetails.getEmail() != null) {
            customer.setEmail(customerDetails.getEmail());
        }
        if (customerDetails.getName() != null) {
            customer.setName(customerDetails.getName());
        }
        if (customerDetails.getNumber() != null) {
            customer.setNumber(customerDetails.getNumber());
        }
        if (customerDetails.getPassword() != null && !customerDetails.getPassword().isEmpty()) {
            // Encrypt the password before saving
            customer.setPassword(passwordEncoder.encode(customerDetails.getPassword()));
        }

        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        Customer customer = getCustomerById(id);
        customerRepository.delete(customer);
    }

    // CRUD methods
}
