package com.controller;

import com.entity.User;
import com.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // ✅ GET all users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // ✅ GET user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ REGISTER (🔥 NEW API)
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {

        Optional<User> existingUser = userService.findByEmail(user.getEmail());

        if (existingUser.isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body("Email already registered ❌");
        }

        user.setRole("author"); // default role
        return ResponseEntity.ok(userService.createUser(user));
    }

    // ✅ CREATE user (optional)
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // ✅ UPDATE user
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        try {
            return ResponseEntity.ok(userService.updateUser(id, userDetails));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ✅ DELETE user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ LOGIN API
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        Optional<User> loggedUser = userService.login(user.getEmail(), user.getPassword());

        if (loggedUser.isPresent()) {
            return ResponseEntity.ok(loggedUser.get());
        } else {
            return ResponseEntity.status(401).body("Invalid email or password");
        }
    }
}