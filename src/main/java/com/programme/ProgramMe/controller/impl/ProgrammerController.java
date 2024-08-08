package com.programme.ProgramMe.controller.impl;

import com.programme.ProgramMe.controller.dto.ProgrammerDTO;
import com.programme.ProgramMe.model.Programmer;
import com.programme.ProgramMe.repository.OnCreate;
import com.programme.ProgramMe.repository.OnUpdate;
import com.programme.ProgramMe.service.impl.ProgrammerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
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
    public ResponseEntity<ProgrammerDTO> createProgrammer(@Validated(OnCreate.class) @RequestBody Programmer programmer) {
        Programmer createdProgrammer = programmerService.createProgrammer(programmer);
        return new ResponseEntity<>(new ProgrammerDTO(createdProgrammer), HttpStatus.CREATED);
    }

    // In ProgrammerController
    @PutMapping("/{id}")
    public ResponseEntity<ProgrammerDTO> updateProgrammer(@PathVariable Long id, @Validated(OnUpdate.class) @RequestBody Programmer programmerDetails) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserEmail = authentication.getName();

        Programmer programmer = programmerService.getProgrammerById(id);
        if (!programmer.getEmail().equals(currentUserEmail)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Programmer updatedProgrammer = programmerService.updateProgrammer(id, programmerDetails);
        return new ResponseEntity<>(new ProgrammerDTO(updatedProgrammer), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgrammer(@PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        Programmer programmer = programmerService.getProgrammerById(id);
        if (programmer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Check if the authenticated programmer is trying to delete their own account
        if (!programmer.getEmail().equals(userEmail)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN); // Return 403 Forbidden if they try to delete another programmer's account
        }

        programmerService.deleteProgrammer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
