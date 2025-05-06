package com.example.demo.service.impl;

import com.example.demo.dao.UserRepository;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.entity.User;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    /**
     * @return созданный пользователь
     */
    @Override
    @Transactional
    public User register(UserDto dto) {

        if (userRepository.findByUsername(dto.getUsername()) == null) {
            User user = new User();
            user.setId(UUID.randomUUID().toString());
            user.setUsername(dto.getUsername());
            return userRepository.save(user);
        }

        throw new IllegalStateException("User already exists: '" + dto.getUsername() + "'");
    }

    /**
     * @return имитация логина
     */
    @Override
    @Transactional(readOnly = true)
    public User login(String username) {
        return userRepository.findByUsername(username);
    }
}
