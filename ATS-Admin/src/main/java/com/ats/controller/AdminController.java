package com.ats.controller;

import com.ats.model.AdminDTO;
import com.ats.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ats")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> register(@RequestParam String email, @RequestParam String password) {
        AdminDTO userDTO = adminService.findByEmail(email);
        if (userDTO != null) {
            return ResponseEntity.status(409).body("User already exists");
        }

        AdminDTO newUserDTO = new AdminDTO();
        newUserDTO.setEmail(email);
        newUserDTO.setPassword(password);
        newUserDTO.setAccountLocked(false);
        newUserDTO.setFailedLoginAttempts(0);
        newUserDTO.setAdmin(false);

        String result = adminService.registerUser(newUserDTO);
        if ("User registered successfully".equals(result)) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(400).body(result);
        }
    }
}
