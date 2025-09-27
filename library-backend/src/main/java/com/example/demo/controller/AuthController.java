package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Key; // <-- New import
import io.jsonwebtoken.security.Keys; // <-- New import
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Map<String, String> userData) {
        if (userRepository.findByEmail(userData.get("email")).isPresent()) {
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }

        User user = new User();
        user.setName(userData.get("name"));
        user.setEmail(userData.get("email"));
        user.setPasswordHash(bCryptPasswordEncoder.encode(userData.get("password")));
        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> loginData) {
        Optional<User> userOptional = userRepository.findByEmail(loginData.get("email"));

        if (userOptional.isEmpty()) {
            return ResponseEntity.status(401).body("Error: Invalid credentials");
        }

        User user = userOptional.get();

        if (bCryptPasswordEncoder.matches(loginData.get("password"), user.getPasswordHash())) {
            // Passwords match, generate JWT
            
            // 1. Create a secure key from your secret string
            Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes());

            // 2. Build the token using the new key
            String token = Jwts.builder()
                    .setSubject(user.getEmail())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 24 hours
                    .signWith(key, SignatureAlgorithm.HS512) // <-- Use the new key object here
                    .compact();

            Map<String, String> response = new HashMap<>();
            response.put("token", token);

            return ResponseEntity.ok(response);
        } else {
            // Passwords do not match
            return ResponseEntity.status(401).body("Error: Invalid credentials");
        }
    }
}