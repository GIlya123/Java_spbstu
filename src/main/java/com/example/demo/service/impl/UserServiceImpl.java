package com.example.demo.service.impl;

import com.example.demo.dao.UserRepository;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.entity.User;
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
    public User register(UserDto dto) {

        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setUsername(dto.getUsername());
        return userRepository.save(user);
    }

    @Override
    public User login(String username) {
        CURRENT_USER = userRepository.findByUsername(username);
        return CURRENT_USER;
    }
}
