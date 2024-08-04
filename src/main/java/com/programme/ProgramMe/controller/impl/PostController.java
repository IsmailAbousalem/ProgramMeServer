package com.programme.ProgramMe.controller.impl;

import com.programme.ProgramMe.model.Post;
import com.programme.ProgramMe.model.Programmer;
import com.programme.ProgramMe.repository.ProgrammerRepository;
import com.programme.ProgramMe.service.impl.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private ProgrammerRepository programmerRepository;

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        Post post = postService.getPostById(id);
        if (post == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody Post post) {
        // Get the authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        // Verify that the user is a programmer
        Programmer programmer = programmerRepository.findByEmail(userEmail);
        if (programmer == null) {
            return new ResponseEntity<>("Only programmers can create posts", HttpStatus.FORBIDDEN);
        }

        post.setProgrammer(programmer);
        Post createdPost = postService.createPost(post);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }
}

