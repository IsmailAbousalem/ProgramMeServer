package com.programme.ProgramMe.controller.impl;

import com.programme.ProgramMe.service.impl.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

    // GET, POST, PUT, DELETE methods
}
