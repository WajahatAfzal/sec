package com.basic.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Override
    public User registerUser(String username, String plainPassword) {

        if (userRepository.findByUsername(username) != null) {
            throw new IllegalArgumentException("Username already exists!");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(plainPassword)); // Hash password
        return userRepository.save(user);
    }

    @Override
    public boolean validateLogin(String username, String plainPassword) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return false;
        }
        return passwordEncoder.matches(plainPassword, user.getPassword()); // Check hashed password
    }
}
