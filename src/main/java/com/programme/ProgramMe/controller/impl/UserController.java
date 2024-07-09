package com.programme.ProgramMe.controller.impl;

import com.programme.ProgramMe.model.User;
import com.programme.ProgramMe.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/api")
public class UserController {
    @Autowired
    IUserService userService;

    //    ********************************************* GET *********************************************

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{user}")
    public User getUserById(@PathVariable String user) {
        return userService.getUserById(user);
    }

//    ********************************************* POST - CREATING INFO/SAVING *********************************************

    @PostMapping("/users")
    public void saveUser(@RequestBody User user) {
        userService.saveUser(user);
    }

}
