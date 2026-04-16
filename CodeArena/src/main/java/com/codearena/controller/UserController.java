package com.codearena.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.codearena.dto.LoginRequest;
import com.codearena.dto.RegisterRequest;
import com.codearena.dto.UserResponse;
import com.codearena.entity.User;
import com.codearena.service.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/users")
    public User createUser(@Valid @RequestBody RegisterRequest request){
        return userService.createUser(request);
    }
    @PostMapping("/auth/login")
    public String login(@RequestBody LoginRequest request){
        return userService.login(request);
    }
    @GetMapping("/users")
    public Page<UserResponse> getUsers(Pageable pageable){
        return userService.getUsers(pageable);
    }
}