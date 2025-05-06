package com.example.demo.dao.jpa;

import com.example.demo.dao.UserRepository;
import com.example.demo.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("jpa")
@RequiredArgsConstructor
public class JpaUserRepository implements UserRepository {

    private final UserJpaRepository jpaRepo;

    @Override
    public User save(User user) {
        return jpaRepo.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return jpaRepo.findByUsername(username);
    }
}
