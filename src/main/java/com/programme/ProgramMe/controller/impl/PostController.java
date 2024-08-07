package com.programme.ProgramMe.controller.impl;

import com.programme.ProgramMe.controller.dto.PostDTO;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private ProgrammerRepository programmerRepository;

    @GetMapping
    public List<PostDTO> getAllPosts() {
        return postService.getAllPosts().stream()
                .map(PostDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Long id) {
        Post post = postService.getPostById(id);
        if (post == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new PostDTO(post), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody Post post) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        Programmer programmer = programmerRepository.findByEmail(userEmail);
        if (programmer == null) {
            return new ResponseEntity<>("Only programmers can create posts", HttpStatus.FORBIDDEN);
        }

        post.setProgrammer(programmer);
        Post createdPost = postService.createPost(post);
        return new ResponseEntity<>(new PostDTO(createdPost), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePost(@PathVariable Long id, @RequestBody Post postDetails) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        // Get the post to be updated
        Post post = postService.getPostById(id);
        if (post == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Ensure only the programmer who created the post can update it
        if (!post.getProgrammer().getEmail().equals(userEmail)) {
            return new ResponseEntity<>("You can only update your own posts", HttpStatus.FORBIDDEN);
        }

        // Update the post details
        post.setTitle(postDetails.getTitle());
        post.setDescription(postDetails.getDescription());
        post.setPrice(postDetails.getPrice());

        // Keep the existing date if no new date is provided
        if (postDetails.getDate() != null) {
            post.setDate(postDetails.getDate());
        }

        Post updatedPost = postService.updatePost(id, post);
        return new ResponseEntity<>(new PostDTO(updatedPost), HttpStatus.OK);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        // Check if the programmer deleting the post is the one who created it
        Post post = postService.getPostById(id);
        if (!post.getProgrammer().getEmail().equals(userEmail)) {
            return new ResponseEntity<>("You can only delete your own posts", HttpStatus.FORBIDDEN);
        }

        postService.deletePost(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

