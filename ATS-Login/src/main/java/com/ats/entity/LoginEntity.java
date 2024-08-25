package com.ats.entity;



import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "login")
public class LoginEntity {
    @Id
    @Column(nullable = false, unique = true)
    private String email;

//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean accountLocked;

    @Column(nullable = false)
    private int failedLoginAttempts;

    @Column(nullable = false)
    private boolean isAdmin;

    private LocalDateTime accountLockedUntil;

    @Column(nullable = false)
    private boolean isLoggedIn;

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
