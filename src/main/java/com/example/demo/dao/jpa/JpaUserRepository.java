package com.example.demo.dao.jpa;

import com.example.demo.dao.UserRepository;
import com.example.demo.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Profile("jpa")
@RequiredArgsConstructor
public class JpaUserRepository implements UserRepository {

    private final UserJpaRepository jpaRepo;

    @Override
    @Transactional
    public User save(User user) {
        return jpaRepo.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        return jpaRepo.findByUsername(username);
    }
}
