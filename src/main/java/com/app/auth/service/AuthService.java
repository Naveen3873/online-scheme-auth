package com.app.auth.service;

import com.app.auth.dto.AuthRequest;
import com.app.auth.dto.AuthResponse;
import com.app.auth.dto.UserDTO;

public interface AuthService {

    public AuthResponse login(AuthRequest request);
    public String register(UserDTO userDTO);
    
}