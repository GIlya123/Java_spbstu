package com.example.demo.service;

import com.example.demo.model.dto.UserDto;
import com.example.demo.model.entity.User;

public interface UserService {

    User register(UserDto user);

    User login(String username);
}
