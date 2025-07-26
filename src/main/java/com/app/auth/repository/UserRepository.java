package com.app.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.auth.entity.User;

public interface UserRepository extends JpaRepository<User, String>{

	Optional<User> findByMobileNumber(String mobileNumber);
	Optional<User> findByEmail(String email);
	
}
