package com.programme.ProgramMe.service.interfaces;

import com.programme.ProgramMe.model.User;

import java.util.List;

public interface IUserService {
    List<User> getAllUsers();

    User getUserById(String user);


    void saveUser(User user);
}
