package com.ats.model;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class LoginDTO {


    private String email;

    private Long id;

    private String password;

    private boolean accountLocked;

    private int failedLoginAttempts;

    private boolean isAdmin;

    private boolean isLoggedIn;

    private LocalDateTime accountLockedUntil;

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
