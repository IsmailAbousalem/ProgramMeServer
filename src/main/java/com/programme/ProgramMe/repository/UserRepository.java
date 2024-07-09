package com.programme.ProgramMe.repository;

import com.programme.ProgramMe.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{


}
