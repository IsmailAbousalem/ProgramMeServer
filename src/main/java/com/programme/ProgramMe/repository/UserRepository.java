package com.programme.ProgramMe.repository;

import com.programme.ProgramMe.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
}
