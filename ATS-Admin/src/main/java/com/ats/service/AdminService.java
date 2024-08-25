package com.ats.service;

import com.ats.entity.AdminEntity;
import com.ats.model.AdminDTO;
import com.ats.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository){
        this.adminRepository = adminRepository;
    }

    public AdminDTO findByEmail(String email) {
        AdminEntity userEntity = adminRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return convertToDTO(userEntity);
    }
    public String registerUser(AdminDTO userDTO) {
        if (!validateEmail(userDTO.getEmail())) {
            return "Invalid email format";
        }

        if (!validatePassword(userDTO.getPassword())) {
            return "Password does not meet security requirements";
        }

        if (adminRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            return "Email is already registered";
        }

        userDTO.setAccountLocked(false);
        userDTO.setFailedLoginAttempts(0);

        AdminEntity user = convertToEntity(userDTO);
        adminRepository.save(user);
        return "User registered successfully";
    }

    private boolean validateEmail(String email) {
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.(com|org|net)$";
        return Pattern.compile(emailRegex).matcher(email).matches();
    }

    private boolean validatePassword(String password) {
        String specialCharacterRegex = "(?=.*[!@#$%^&*(),.?\":{}|<>])";
        String capitalLetterRegex = "(?=.*[A-Z])";
        String digitRegex = "(?=.*\\d)";
        String lengthRegex = ".{10,}";

        return Pattern.compile(specialCharacterRegex).matcher(password).find()
                && Pattern.compile(capitalLetterRegex).matcher(password).find()
                && Pattern.compile(digitRegex).matcher(password).find()
                && Pattern.compile(lengthRegex).matcher(password).matches();
    }

    private AdminEntity convertToEntity(AdminDTO userDTO) {
        AdminEntity user = new AdminEntity();
        user.setEmail(userDTO.getEmail());
        user.setId(userDTO.getId());
        user.setPassword(userDTO.getPassword());
        user.setAccountLocked(userDTO.isAccountLocked());
        user.setFailedLoginAttempts(userDTO.getFailedLoginAttempts());
        user.setAdmin(userDTO.isAdmin());
        user.setLoggedIn(userDTO.isLoggedIn());
        user.setAccountLockedUntil(userDTO.getAccountLockedUntil());
        return user;
    }
    private AdminDTO convertToDTO(AdminEntity user) {
        AdminDTO dto = new AdminDTO();
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
}
