package com.example.demo.dao.impl;

import com.example.demo.dao.UserRepository;
import com.example.demo.model.User;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.Map;

@Repository
@Profile("in-memory")
public class InMemoryUserRepository implements UserRepository {

    private final Map<String, User> users = new LinkedHashMap<>();

    @Override
    public User save(User user) {
        users.put(user.getUsername(), user);
        return user;
    }

    @Override
    public User findByUsername(String username) {
        return users.get(username);
    }
}
