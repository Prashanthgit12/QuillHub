package com.service;

import com.entity.User;
import com.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ✅ GET all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // ✅ GET user by ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // ✅ CREATE user
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // ✅ FIND BY EMAIL (🔥 REQUIRED FOR REGISTER)
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // ✅ UPDATE user
    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        user.setRole(userDetails.getRole());
        user.setStatus(userDetails.getStatus());

        return userRepository.save(user);
    }

    // ✅ DELETE user
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // ✅ LOGIN
    public Optional<User> login(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return user;
        }

        return Optional.empty();
    }
}