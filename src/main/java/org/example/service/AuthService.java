package org.example.service;

import org.example.dto.UserDto;

import java.util.List;

public class AuthService {

    private final UserService userService;
    private static UserDto currentUser;

    public AuthService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Temporary login checker
     */
    public boolean login(String usernameOrEmail, String password) {
        // 1. Temporary Admin Credentials
        if ("admin".equalsIgnoreCase(usernameOrEmail.trim()) && "admin123".equals(password)) {
            currentUser = new UserDto("0", "Administrator", "admin@example.com");
            return true;
        }

        // 2. Check existing Database Users (Default password: "123456")
        List<UserDto> users = userService.getAllUsers();
        for (UserDto user : users) {
            if (user.getEmail().equalsIgnoreCase(usernameOrEmail.trim()) && "123456".equals(password)) {
                currentUser = user;
                return true;
            }
        }

        return false; // Invalid credentials
    }

    public static UserDto getCurrentUser() {
        return currentUser;
    }

    public static void logout() {
        currentUser = null;
    }
}