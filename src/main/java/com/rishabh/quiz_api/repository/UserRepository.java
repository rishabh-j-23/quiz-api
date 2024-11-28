package com.rishabh.quiz_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rishabh.quiz_api.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User getUserById(long id);
}
