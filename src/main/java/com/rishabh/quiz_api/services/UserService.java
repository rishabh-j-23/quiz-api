package com.rishabh.quiz_api.services;

import org.springframework.stereotype.Service;

import com.rishabh.quiz_api.models.User;
import com.rishabh.quiz_api.repository.UserRepository;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(long id) throws Exception {
        return userRepository.findById(id)
                .orElseThrow(() -> new Exception("user not found with id: " + id));
    }

    public User createUser(User user) {
        if (userRepository.findById(user.getId()).isPresent()) {
            return user;
        }
        return userRepository.save(user);
    }

}
