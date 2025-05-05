package com.example.demo.service.impl;

import com.example.demo.dao.UserRepository;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Getter
    private static User CURRENT_USER;

    private final UserRepository userRepository;

    @Override
    public User register(User user) {
        user.setId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    @Override
    public User login(String username) {
        CURRENT_USER = userRepository.findByUsername(username);
        return CURRENT_USER;
    }
}
