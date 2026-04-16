package com.codearena.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.codearena.dto.LoginRequest;
import com.codearena.dto.RegisterRequest;
import com.codearena.dto.UserResponse;
import com.codearena.entity.Role;
import com.codearena.entity.User;
import com.codearena.repository.UserRepository;
import com.codearena.security.JwtUtil;

@Service
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	private final JwtUtil jwtUtil;
	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;

	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtUtil = jwtUtil;
	}

	public User createUser(RegisterRequest request) {

		logger.info("New user registration attempt: {}", request.getEmail());
		if (userRepository.findByEmail(request.getEmail()).isPresent()) {
			throw new RuntimeException("Email already exists");
		}
		User user = new User();

		user.setUsername(request.getUsername());
		user.setEmail(request.getEmail());

		String hashedPassword = passwordEncoder.encode(request.getPassword());
		user.setPassword(hashedPassword);

		user.setRole(Role.USER);

		User savedUser = userRepository.save(user);

		logger.info("User registered successfully: {}", savedUser.getEmail());

		return savedUser;
	}

	public String login(LoginRequest request) {

		logger.info("Login attempt for email: {}", request.getEmail());

		User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> {
			logger.warn("Login failed - user not found: {}", request.getEmail());
			return new RuntimeException("User not found");
		});

		boolean passwordMatch = passwordEncoder.matches(request.getPassword(), user.getPassword());

		if (!passwordMatch) {
			logger.warn("Invalid password for user: {}", request.getEmail());
			throw new RuntimeException("Invalid password");
		}

		logger.info("User logged in successfully: {}", request.getEmail());

		return jwtUtil.generateToken(user.getEmail());
	}

	public Page<UserResponse> getUsers(Pageable pageable) {

		return userRepository.findAll(pageable).map(user -> new UserResponse(user.getUsername(), user.getEmail()));
	}
}
