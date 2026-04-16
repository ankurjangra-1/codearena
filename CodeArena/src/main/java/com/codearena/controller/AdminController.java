package com.codearena.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @GetMapping("/admin/test")
    public String adminOnly() {
        return "Admin API working 🔥";
    }
}
