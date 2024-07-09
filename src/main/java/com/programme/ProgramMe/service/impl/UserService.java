package com.programme.ProgramMe.service.impl;

import com.programme.ProgramMe.model.User;
import com.programme.ProgramMe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.programme.ProgramMe.service.interfaces.IUserService;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService{
    @Autowired
    UserRepository userRepository;


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String user) {
        Optional<User> userOptional = userRepository.findById(Long.valueOf(user));
        if (userOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User " + user + " not found");
        return userOptional.get();
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }


}
