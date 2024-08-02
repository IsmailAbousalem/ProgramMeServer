package com.programme.ProgramMe.service.impl;

import com.programme.ProgramMe.model.Post;
import com.programme.ProgramMe.model.PostImage;
import com.programme.ProgramMe.repository.PostImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PostImageService {

    @Autowired
    private PostImageRepository postImageRepository;

    public PostImage storeImage(MultipartFile file, Post post) throws IOException {
        // Create a new PostImage entity
        PostImage postImage = new PostImage();
        postImage.setFileName(file.getOriginalFilename());
        postImage.setFileData(file.getBytes());  // Store file data as byte array
        postImage.setPost(post);

        // Save the PostImage entity to the database
        return postImageRepository.save(postImage);
    }
}

