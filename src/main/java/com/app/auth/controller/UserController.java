package com.app.auth.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.auth.dto.UserDTO;
import com.app.auth.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> getAll() {
		return ResponseEntity.ok(userService.getAllUsers());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getById(@PathVariable String id) {
		UserDTO dto = userService.getUserById(id);
		return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO dto) {
		return ResponseEntity.ok(userService.createUser(dto));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserDTO> updateUser(@PathVariable String id, @RequestBody UserDTO dto) {
		UserDTO updated = userService.updateUser(id, dto);
		return updated != null ? ResponseEntity.ok(null) : ResponseEntity.notFound().build();
	}
	
//	@DeleteMapping("/{id}")
//	public ResponseEntity<Void> deleteUser(@PathVariable String id) {
//		userService.deleteUser(id);
//		return ResponseEntity.noContent().build();
//	}

}
