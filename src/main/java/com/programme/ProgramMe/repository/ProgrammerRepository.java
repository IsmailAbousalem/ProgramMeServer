package com.programme.ProgramMe.repository;

import com.programme.ProgramMe.model.Programmer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgrammerRepository extends JpaRepository<Programmer, Long>{
}
