package com.programme.ProgramMe.controller.impl;

import com.programme.ProgramMe.config.JwtUtil;
import com.programme.ProgramMe.config.UserDetailsServiceImpl;
import com.programme.ProgramMe.model.AuthenticationRequest;
import com.programme.ProgramMe.model.AuthenticationResponse;
import com.programme.ProgramMe.model.Customer;
import com.programme.ProgramMe.model.Programmer;
import com.programme.ProgramMe.repository.CustomerRepository;
import com.programme.ProgramMe.repository.ProgrammerRepository;
import com.programme.ProgramMe.model.SignupRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProgrammerRepository programmerRepository;

//    ProgramMe
//    Play on word Programme

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupRequest signupRequest) {
        if ("programmer".equalsIgnoreCase(signupRequest.getUserType())) {
            if (programmerRepository.findByEmail(signupRequest.getEmail()) != null) {
                return ResponseEntity.badRequest().body("Programmer already exists");
            }
            Programmer programmer = new Programmer();
            programmer.setName(signupRequest.getName());
            programmer.setEmail(signupRequest.getEmail());
            programmer.setNumber(signupRequest.getNumber());
            programmer.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
            programmer.setSkills(signupRequest.getSkills());
            programmer.setDescription(signupRequest.getDescription());
            programmerRepository.save(programmer);
            return ResponseEntity.ok(programmer);
        } else {
            if (customerRepository.findByEmail(signupRequest.getEmail()) != null) {
                return ResponseEntity.badRequest().body("Customer already exists");
            }
            Customer customer = new Customer();
            customer.setName(signupRequest.getName());
            customer.setEmail(signupRequest.getEmail());
            customer.setNumber(signupRequest.getNumber());
            customer.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
            customerRepository.save(customer);
            return ResponseEntity.ok(customer);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect email or password", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @GetMapping("/verify")
    public ResponseEntity<?> verifyToken(HttpServletRequest request) {
        final String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String jwt = authorizationHeader.substring(7);
            String username = jwtUtil.extractUsername(jwt);

            if (username != null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                if (jwtUtil.validateToken(jwt, userDetails)) {
                    return ResponseEntity.ok(userDetails);
                }
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
