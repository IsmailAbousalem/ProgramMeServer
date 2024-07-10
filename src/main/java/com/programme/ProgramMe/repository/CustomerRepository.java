package com.programme.ProgramMe.repository;

import com.programme.ProgramMe.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
}
