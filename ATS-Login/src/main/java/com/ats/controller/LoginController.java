package com.ats.controller;



import com.ats.feign_client.PassengerClient;
import com.ats.model.LoginDTO;
import com.ats.model.PassengerDTO;
import com.ats.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/ats")
public class LoginController {

    private final LoginService loginService;
    private final PassengerClient passengerClient;

    @Autowired
    public LoginController(LoginService loginService, PassengerClient passengerClient){
        this.loginService = loginService;
        this.passengerClient = passengerClient;
    }



    @PostMapping("/login")
    public ResponseEntity<List<PassengerDTO>> login(@RequestParam String email, @RequestParam String password) {
        LoginDTO userDTO = loginService.findByEmail(email);

        // Check if the email exists
        if (userDTO == null) {
            return ResponseEntity.status(401).build();//"Invalid email or password"
        }

        // Check if the account is locked
        if (userDTO.isAccountLocked()) {
            if (userDTO.getAccountLockedUntil() != null && LocalDateTime.now().isAfter(userDTO.getAccountLockedUntil())) {
                loginService.resetFailedLoginAttempts(userDTO); // Unlock the account if lock period is over
            } else {
                return ResponseEntity.status(403).build();//"Account is locked. Please try again later."
            }
        }

        // Check if the admin is already logged in on another server
        if (userDTO.isLoggedIn()) {
            return ResponseEntity.status(403).build();//"Admin is already logged in on another server."
        }

        // Validate the password
        if (loginService.validatePassword(password, userDTO.getPassword())) {
            loginService.resetFailedLoginAttempts(userDTO); // Reset failed login attempts on successful login
            loginService.markAsLoggedIn(userDTO); // Mark the user as logged in



            List<PassengerDTO> passengers = passengerClient.getAllPassengers();

            return ResponseEntity.ok(passengers);
        } else {
            loginService.increaseFailedLoginAttempts(userDTO); // Increase failed login attempts
            return ResponseEntity.status(401).build();//("Invalid email or password")
        }
    }






    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestParam String email) {
        LoginDTO userDTO = loginService.findByEmail(email);

        if (userDTO == null) {
            return ResponseEntity.status(401).body("Invalid email");
        }

        loginService.markAsLoggedOut(userDTO);
        return ResponseEntity.ok("Logout successful");
    }



}
