package com.programme.ProgramMe.service.impl;

import com.programme.ProgramMe.model.Programmer;
import com.programme.ProgramMe.repository.ProgrammerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgrammerService {
    @Autowired
    private ProgrammerRepository programmerRepository;

    public List<Programmer> getAllProgrammers() {
        return programmerRepository.findAll();
    }

    public Programmer getProgrammerById(Long id) {
        return programmerRepository.findById(id).orElseThrow(() -> new RuntimeException("Programmer not found"));
    }

    public Programmer createProgrammer(Programmer programmer) {
        return programmerRepository.save(programmer);
    }

    public Programmer updateProgrammer(Long id, Programmer programmerDetails) {
        Programmer programmer = getProgrammerById(id);
        programmer.setEmail(programmerDetails.getEmail());
        programmer.setName(programmerDetails.getName());
        programmer.setNumber(programmerDetails.getNumber());
        programmer.setPassword(programmerDetails.getPassword());
        programmer.setDescription(programmerDetails.getDescription());
        programmer.setSkills(programmerDetails.getSkills());
        return programmerRepository.save(programmer);
    }

    public void deleteProgrammer(Long id) {
        Programmer programmer = getProgrammerById(id);
        programmerRepository.delete(programmer);
    }


    // CRUD methods
}
