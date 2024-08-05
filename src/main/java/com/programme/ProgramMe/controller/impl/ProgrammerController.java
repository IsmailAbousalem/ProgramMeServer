package com.programme.ProgramMe.controller.impl;

import com.programme.ProgramMe.controller.dto.ProgrammerDTO;
import com.programme.ProgramMe.model.Programmer;
import com.programme.ProgramMe.service.impl.ProgrammerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/programmers")
public class ProgrammerController {

    @Autowired
    private ProgrammerService programmerService;

    @GetMapping
    public List<ProgrammerDTO> getAllProgrammers() {
        return programmerService.getAllProgrammers().stream()
                .map(ProgrammerDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgrammerDTO> getProgrammerById(@PathVariable Long id) {
        Programmer programmer = programmerService.getProgrammerById(id);
        if (programmer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ProgrammerDTO(programmer), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProgrammerDTO> createProgrammer(@Valid @RequestBody Programmer programmer) {
        Programmer createdProgrammer = programmerService.createProgrammer(programmer);
        return new ResponseEntity<>(new ProgrammerDTO(createdProgrammer), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProgrammerDTO> updateProgrammer(@PathVariable Long id, @Valid @RequestBody Programmer programmerDetails) {
        Programmer updatedProgrammer = programmerService.updateProgrammer(id, programmerDetails);
        return new ResponseEntity<>(new ProgrammerDTO(updatedProgrammer), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgrammer(@PathVariable Long id) {
        programmerService.deleteProgrammer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
