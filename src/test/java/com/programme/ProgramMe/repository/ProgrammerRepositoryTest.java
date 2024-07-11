package com.programme.ProgramMe.repository;

import com.programme.ProgramMe.model.Programmer;
import com.programme.ProgramMe.repository.ProgrammerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProgrammerRepositoryTest {

    @Autowired
    ProgrammerRepository programmerRepository;

    Programmer programmer;

    @BeforeEach
    void setUp() {
        programmer = new Programmer();
        programmer.setEmail("testprogrammer@example.com");
        programmer.setName("Test Programmer");
        programmer.setNumber("0987654321");
        programmer.setPassword("password");
        programmer.setDescription("Experienced in Java");
        programmer.setSkills("Java, Spring Boot");

        programmerRepository.save(programmer);
    }

    @AfterEach
    void tearDown() {
        programmerRepository.deleteById(programmer.getId());
    }

    @Test
    public void findAll_programmers_programmerList() {
        List<Programmer> programmerList = programmerRepository.findAll();
        assertFalse(programmerList.isEmpty());
    }

    @Test
    public void findById_validId_correctProgrammer() {
        Optional<Programmer> programmerOptional = programmerRepository.findById(programmer.getId());
        assertTrue(programmerOptional.isPresent());
        assertEquals(programmer.getEmail(), programmerOptional.get().getEmail());
    }

    @Test
    public void findById_invalidId_programmerNotPresent() {
        Optional<Programmer> programmerOptional = programmerRepository.findById(999L);
        assertTrue(programmerOptional.isEmpty());
    }
}
