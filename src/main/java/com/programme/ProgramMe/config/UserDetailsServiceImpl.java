package com.programme.ProgramMe.config;

import com.programme.ProgramMe.model.Programmer;
import com.programme.ProgramMe.repository.ProgrammerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.programme.ProgramMe.model.Customer;
import com.programme.ProgramMe.repository.CustomerRepository;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProgrammerRepository programmerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // First, try to load as a Customer
        Customer customer = customerRepository.findByEmail(email);
        if (customer != null) {
            return new org.springframework.security.core.userdetails.User(customer.getEmail(), customer.getPassword(), new ArrayList<>());
        }

        // If not found as a Customer, try to load as a Programmer
        Programmer programmer = programmerRepository.findByEmail(email);
        if (programmer != null) {
            return new org.springframework.security.core.userdetails.User(programmer.getEmail(), programmer.getPassword(), new ArrayList<>());
        }

        throw new UsernameNotFoundException("User not found");
    }

    public String getUserType(String email) {
        if (programmerRepository.findByEmail(email) != null) {
            return "programmer";
        } else if (customerRepository.findByEmail(email) != null) {
            return "customer";
        } else {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
    }
}

