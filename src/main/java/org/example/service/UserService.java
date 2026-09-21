package org.example.service;

import org.example.dao.UserDAO;
import org.example.dto.UserDto;
import org.example.mapper.UserMapper;
import org.example.model.User;
import org.example.security.CryptoUtil;

import java.util.List;

public class UserService {

    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    // CREATE USER
    public void createUser(String name, String email) {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                    "Name cannot be null or blank"
            );
        }

        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException(
                    "Name cannot be null or blank"
            );
        }

        User user = new User(name, email);

        userDAO.save(user);
    }

    // GET ALL USERS
    public List<UserDto> getAllUsers() {
//        return userDAO.findAll();

        List<User> users = userDAO.findAll();

        return users.stream()
                .map(UserMapper::toDto)
                .toList();
    }

    // GET USER BY ID
    public User getUserById(String encryptedId) {

        int id = Integer.parseInt(
                CryptoUtil.decrypt(encryptedId)
        );

        return userDAO.findById(id);
    }

    // UPDATE USER
    public void updateUser(String encryptedId, String name, String email) {

        int id = Integer.parseInt(
                CryptoUtil.decrypt(encryptedId)
        );

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                    "Name cannot be null or blank"
            );
        }

        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException(
                    "Name cannot be null or blank"
            );
        }

        User user = new User(id, name, email);

        userDAO.update(user);
    }

    // DELETE USER BY ID
    public void deleteUserById(String encryptedId) {

        int id = Integer.parseInt(
                CryptoUtil.decrypt(encryptedId)
        );

        userDAO.delete(id);
    }
}
