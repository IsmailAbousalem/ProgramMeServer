package com.programme.ProgramMe.controller.impl;

import com.programme.ProgramMe.model.Post;
import com.programme.ProgramMe.model.PostImage;
import com.programme.ProgramMe.service.impl.PostService;
import com.programme.ProgramMe.service.impl.PostImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/posts/{postId}/images")
public class PostImageController {

    @Autowired
    private PostService postService;

    @Autowired
    private PostImageService postImageService;

    @PostMapping
    public ResponseEntity<?> uploadImage(@PathVariable Long postId, @RequestParam("file") MultipartFile file) {
        // Find the post by ID
        Post post = postService.getPostById(postId);
        if (post == null) {
            return new ResponseEntity<>("Post not found", HttpStatus.NOT_FOUND);
        }

        try {
            // Store the image and link it to the post
            PostImage postImage = postImageService.storeImage(file, post);
            return new ResponseEntity<>(postImage, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to upload image", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
