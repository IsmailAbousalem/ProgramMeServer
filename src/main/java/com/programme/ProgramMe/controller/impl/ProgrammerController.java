package com.programme.ProgramMe.controller.impl;

import com.programme.ProgramMe.service.impl.ProgrammerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/programmers")
public class ProgrammerController {
    @Autowired
    private ProgrammerService programmerService;

    // GET, POST, PUT, DELETE methods
}
