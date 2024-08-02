package com.programme.ProgramMe.controller.impl;

import com.programme.ProgramMe.model.Customer;
import com.programme.ProgramMe.service.impl.ProfileService;
import io.jsonwebtoken.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping("/{customerId}/upload-profile-picture")
    public ResponseEntity<?> uploadProfilePicture(@PathVariable Long customerId, @RequestParam("file") MultipartFile file) {
        try {
            Customer updatedCustomer = profileService.uploadProfilePicture(customerId, file);
            return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to upload profile picture", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }
    }
}
