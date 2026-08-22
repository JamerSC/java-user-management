package org.example.service;

import org.example.dao.UserDAO;
import org.example.model.User;

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
    public List<User> getAllUsers() {
        return userDAO.findAll();
    }

    // GET USER BY ID
    public User getUserById(int id) {
        return userDAO.findById(id);
    }

    // UPDATE USER
    public void updateUser(int id, String name, String email) {
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
    public void deleteUserById(int id) {
        userDAO.delete(id);
    }
}
