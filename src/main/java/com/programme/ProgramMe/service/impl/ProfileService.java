package com.programme.ProgramMe.service.impl;

import com.programme.ProgramMe.model.Customer;
import com.programme.ProgramMe.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ProfileService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer uploadProfilePicture(Long customerId, MultipartFile file) throws IOException {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // Store the profile picture directly in the Customer entity
        customer.setProfilePicture(file.getBytes());  // Assuming you add this field to Customer entity
        customer.setProfilePictureFilename(file.getOriginalFilename());

        // Save the updated customer entity
        return customerRepository.save(customer);
    }
}

