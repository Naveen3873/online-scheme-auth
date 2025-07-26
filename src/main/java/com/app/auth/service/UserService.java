package com.app.auth.service;

import java.util.List;
import java.util.Map;

import com.app.auth.dto.UserDTO;

public interface UserService {

	List<UserDTO> getAllUsers();
    UserDTO getUserById(String id);
    UserDTO createUser(UserDTO dto);
    UserDTO updateUser(String id, UserDTO dto);
    void deleteUser(String id);

}
