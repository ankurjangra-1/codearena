package com.codearena.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codearena.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);
}