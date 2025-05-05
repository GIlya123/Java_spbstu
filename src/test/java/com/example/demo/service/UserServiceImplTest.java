package com.example.demo.service;

import com.example.demo.dao.UserRepository;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.entity.User;
import com.example.demo.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserServiceImplTest {

    private UserRepository userRepository;
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    void register() {
        UserDto dto = new UserDto();
        dto.setUsername("test");

        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArguments()[0]);

        User savedUser = userService.register(dto);

        assertNotNull(savedUser.getId());
        assertEquals("test", savedUser.getUsername());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void login() {
        User user = new User();
        user.setUsername("user1");
        when(userRepository.findByUsername("user1")).thenReturn(user);

        User result = userService.login("user1");

        assertEquals("user1", result.getUsername());
    }
}
