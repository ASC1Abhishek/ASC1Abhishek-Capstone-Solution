package com.ats.service;

import com.ats.entity.LoginEntity;
import com.ats.model.LoginDTO;
import com.ats.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class LoginService {

    private final LoginRepository loginRepository;

    @Autowired
    public LoginService(LoginRepository loginRepository){
        this.loginRepository = loginRepository;
    }

    public LoginDTO findByEmail(String email) {
        LoginEntity userEntity = loginRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return convertToDTO(userEntity);
    }


    public boolean validatePassword(String rawPassword, String storedPassword) {
        return rawPassword.equals(storedPassword);
    }

    public void increaseFailedLoginAttempts(LoginDTO userDTO) {
        LoginEntity user = convertToEntity(userDTO);
        user.setFailedLoginAttempts(user.getFailedLoginAttempts() + 1);
        if (user.getFailedLoginAttempts() >= 3) {
            user.setAccountLocked(true);
            user.setAccountLockedUntil(LocalDateTime.now().plusMinutes(30));
        }

        loginRepository.save(user);
    }

    public void resetFailedLoginAttempts(LoginDTO userDTO) {
        LoginEntity user = convertToEntity(userDTO);
        user.setFailedLoginAttempts(0);
        user.setAccountLocked(false);
        loginRepository.save(user);
    }


    public void markAsLoggedIn(LoginDTO userDTO) {
        LoginEntity user = convertToEntity(userDTO);
        user.setLoggedIn(true);
        loginRepository.save(user);
    }

    public void markAsLoggedOut(LoginDTO userDTO) {
        LoginEntity user = convertToEntity(userDTO);
        user.setLoggedIn(false);
        loginRepository.save(user);
    }


    private LoginDTO convertToDTO(LoginEntity user) {
        LoginDTO dto = new LoginDTO();
        dto.setEmail(user.getEmail());
        dto.setId(user.getId());
        dto.setAccountLocked(user.isAccountLocked());
        dto.setFailedLoginAttempts(user.getFailedLoginAttempts());
        dto.setAdmin(user.isAdmin());
        dto.setAccountLockedUntil(user.getAccountLockedUntil());
        dto.setPassword(user.getPassword());
        dto.setLoggedIn(user.isLoggedIn());

        return dto;
    }

    private LoginEntity convertToEntity(LoginDTO userDTO) {
        LoginEntity user = new LoginEntity();
        user.setEmail(userDTO.getEmail());
        user.setId(userDTO.getId());
        user.setPassword(userDTO.getPassword());
        user.setAccountLocked(userDTO.isAccountLocked());
        user.setFailedLoginAttempts(userDTO.getFailedLoginAttempts());
        user.setAdmin(userDTO.getIsAdmin());
        user.setLoggedIn(userDTO.isLoggedIn());
        user.setAccountLockedUntil(userDTO.getAccountLockedUntil());
        return user;
    }
}
