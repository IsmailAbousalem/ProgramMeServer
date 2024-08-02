package com.programme.ProgramMe.controller.impl;

import com.programme.ProgramMe.model.Programmer;
import com.programme.ProgramMe.service.impl.ProgrammerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/programmers")
public class ProgrammerController {
    @Autowired
    private ProgrammerService programmerService;

    @GetMapping
    public List<Programmer> getAllProgrammers() {
        return programmerService.getAllProgrammers();
    }

    @GetMapping("/{id}")
    public Programmer getProgrammerById(@PathVariable Long id) {
        return programmerService.getProgrammerById(id);
    }

    @PostMapping
    public ResponseEntity<Programmer> createProgrammer(@Valid @RequestBody Programmer programmer) {
        Programmer createdProgrammer = programmerService.createProgrammer(programmer);
        return new ResponseEntity<>(createdProgrammer, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Programmer> updateProgrammer(@PathVariable Long id, @Valid @RequestBody Programmer programmerDetails) {
        Programmer updatedProgrammer = programmerService.updateProgrammer(id, programmerDetails);
        return new ResponseEntity<>(updatedProgrammer, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgrammer(@PathVariable Long id) {
        programmerService.deleteProgrammer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
