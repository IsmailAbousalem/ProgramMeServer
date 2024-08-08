package com.programme.ProgramMe.service.impl;

import com.programme.ProgramMe.model.Programmer;
import com.programme.ProgramMe.repository.ProgrammerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgrammerService {
    @Autowired
    private ProgrammerRepository programmerRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

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

        if (programmerDetails.getEmail() != null) {
            programmer.setEmail(programmerDetails.getEmail());
        }
        if (programmerDetails.getName() != null) {
            programmer.setName(programmerDetails.getName());
        }
        if (programmerDetails.getNumber() != null) {
            programmer.setNumber(programmerDetails.getNumber());
        }
        if (programmerDetails.getPassword() != null && !programmerDetails.getPassword().isEmpty()) {
            // Encrypt the password before saving
            programmer.setPassword(passwordEncoder.encode(programmerDetails.getPassword()));
        }
        if (programmerDetails.getDescription() != null) {
            programmer.setDescription(programmerDetails.getDescription());
        }
        if (programmerDetails.getSkills() != null) {
            programmer.setSkills(programmerDetails.getSkills());
        }

        return programmerRepository.save(programmer);
    }


    public void deleteProgrammer(Long id) {
        Programmer programmer = getProgrammerById(id);
        programmerRepository.delete(programmer);
    }


    // CRUD methods
}
