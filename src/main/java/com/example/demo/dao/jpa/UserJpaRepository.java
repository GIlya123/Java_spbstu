package com.example.demo.dao.jpa;

import com.example.demo.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
}
