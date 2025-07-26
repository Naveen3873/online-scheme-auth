package com.app.auth.service.impl;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.auth.dto.AuthRequest;
import com.app.auth.dto.AuthResponse;
import com.app.auth.dto.UserDTO;
import com.app.auth.entity.Role;
import com.app.auth.entity.User;
import com.app.auth.entity.UserCodeSequence;
import com.app.auth.repository.RoleRepository;
import com.app.auth.repository.UserCodeSequenceRepository;
import com.app.auth.repository.UserRepository;
import com.app.auth.security.JwtUtil;
import com.app.auth.service.AuthService;

import jakarta.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authManager;
    private final UserRepository userRepository;
    private final UserCodeSequenceRepository UserCodeSequenceRepository;
    private final RoleRepository roleRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse login(AuthRequest request) {
        authManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getMobileNumber(), request.getPassword())
        );

        String token = jwtUtil.generateToken(request.getMobileNumber());
     // 🔍 Find the user and store the token
        Optional<User> optionalUser = userRepository.findByMobileNumber(request.getMobileNumber());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setToken(token);
            userRepository.save(user); // ✅ Save the updated token
        }
        return new AuthResponse(token);
    }

    public String register(UserDTO userDTO) {
        // Optional: check if mobile already exists
        Optional<User> userExist = userRepository.findByMobileNumber(userDTO.getMobileNumber());
        if (userExist.isPresent()) {
            throw new IllegalArgumentException("Mobile number already registered.");
        }

        Role userRole = roleRepository.findByName("USER")
            .orElseThrow(() -> new RuntimeException("Default role USER not found in database"));
        
        String code = generateUserCode();
        User user = User.builder()
                .name(userDTO.getName())
                .code(code)
                .email(userDTO.getEmail())
                .mobileNumber(userDTO.getMobileNumber())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .roles(Set.of(userRole))
                .build();

        userRepository.save(user);
        return "User registered successfully";
    }
    
    @Transactional
    public String generateUserCode() {
        LocalDate now = LocalDate.now();
        int year = now.getYear() % 100;   // last 2 digits (e.g., 2025 → 25)
        int month = now.getMonthValue();  // e.g., 7

        UserCodeSequence sequence = UserCodeSequenceRepository
            .findByYearAndMonth(year, month)
            .orElseGet(() -> UserCodeSequence.builder()
                    .year(year)
                    .month(month)
                    .count(0)
                    .build()
            );

        // Increment safely
        int nextCount = sequence.getCount() + 1;
        sequence.setCount(nextCount);

        UserCodeSequenceRepository.save(sequence); // will auto update due to @Transactional

        return String.format("APP%02d%02d%02d", year, month, nextCount); // e.g., APP250701
    }
}