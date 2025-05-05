package com.example.demo.dao;

import com.example.demo.model.User;

public interface UserRepository {

    User save(User user);

    User findByUsername(String username);
}
