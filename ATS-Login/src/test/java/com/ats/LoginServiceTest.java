package com.ats;

import com.ats.entity.LoginEntity;
import com.ats.model.LoginDTO;
import com.ats.repository.LoginRepository;
import com.ats.service.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class LoginServiceTest {

    @Autowired
    private LoginService loginService;

    @Autowired
    private LoginRepository loginRepository;

    @BeforeEach
    void setUp() {
        // Setup database with initial data for testing
        createTestUser("abhishek.1010@gmail.com", 3, "abhishek1010", false, 0, false, true, null);
        createTestUser("ad@example.com", 4, "Ad@123", false, 0, false, false, null);
        createTestUser("ad1@example.com", 6, "Ad1@123", false, 0, false, false, null);
        createTestUser("admin@example.com", 1, "Admin@123", true, 3, true, false, LocalDateTime.now().plusMinutes(30));
        createTestUser("bawa@gmail.com", 5, "bawaTheMattii", false, 0, false, true, null);
        createTestUser("user@example.com", 2, "User@123", false, 0, false, true, null);
    }


    private void createTestUser(String email, int id, String password, boolean accountLocked, int failedLoginAttempts,
                                boolean isAdmin, boolean isLoggedIn, LocalDateTime accountLockedUntil) {
        LoginEntity userEntity = new LoginEntity();
        userEntity.setEmail(email);
        userEntity.setId((long) id);
        userEntity.setPassword(password);
        userEntity.setAccountLocked(accountLocked);
        userEntity.setFailedLoginAttempts(failedLoginAttempts);
        userEntity.setAdmin(isAdmin);
        userEntity.setLoggedIn(isLoggedIn);
        userEntity.setAccountLockedUntil(accountLockedUntil);
        loginRepository.save(userEntity);
    }

    @Test
    void testFindByEmail() {
        // Act
        LoginDTO result = loginService.findByEmail("abhishek.1010@gmail.com");

        // Assert
        assertNotNull(result);
        assertEquals("abhishek.1010@gmail.com", result.getEmail());
        assertEquals(3, result.getId());
    }

    @Test
    void testValidateCorrectPassword() {
        // Arrange
        String rawPassword = "abhishek1010";
        String storedPassword = "abhishek1010";

        // Act
        boolean isValid = loginService.validatePassword(rawPassword, storedPassword);

        // Assert
        assertTrue(isValid);
    }

    @Test
    void testValidateIncorrectPassword() {
        // Arrange
        String rawPassword = "wrongPassword";
        String storedPassword = "abhishek1010";

        // Act
        boolean isValid = loginService.validatePassword(rawPassword, storedPassword);

        // Assert
        assertFalse(isValid);
    }



    @Test
    void testResetFailedLoginAttempts() {
        // Arrange
        LoginDTO userDTO = loginService.findByEmail("admin@example.com");

        // Act
        loginService.resetFailedLoginAttempts(userDTO);
        LoginEntity updatedUser = loginRepository.findByEmail("admin@example.com")
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Assert
        assertEquals(0, updatedUser.getFailedLoginAttempts());
        assertFalse(updatedUser.isAccountLocked());
    }

    @Test
    void testMarkAsLoggedIn() {
        // Arrange
        LoginDTO userDTO = loginService.findByEmail("ad1@example.com");

        // Act
        loginService.markAsLoggedIn(userDTO);
        LoginEntity updatedUser = loginRepository.findByEmail("ad1@example.com")
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Assert
        assertTrue(updatedUser.isLoggedIn());
    }

    @Test
    void testMarkAsLoggedOut() {
        // Arrange
        LoginDTO userDTO = loginService.findByEmail("bawa@gmail.com");

        // Act
        loginService.markAsLoggedOut(userDTO);
        LoginEntity updatedUser = loginRepository.findByEmail("bawa@gmail.com")
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Assert
        assertFalse(updatedUser.isLoggedIn());
    }
}
