package com.programme.ProgramMe.controller.impl;

import com.programme.ProgramMe.model.Programmer;
import com.programme.ProgramMe.service.impl.ProgrammerService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Programmer createProgrammer(@RequestBody Programmer programmer) {
        return programmerService.createProgrammer(programmer);
    }

    @PutMapping("/{id}")
    public Programmer updateProgrammer(@PathVariable Long id, @RequestBody Programmer programmerDetails) {
        return programmerService.updateProgrammer(id, programmerDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteProgrammer(@PathVariable Long id) {
        programmerService.deleteProgrammer(id);
    }

}
