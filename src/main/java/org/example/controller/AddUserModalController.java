package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.model.User;
import org.example.service.UserService;

public class AddUserModalController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @FXML
    private void handleAddUser() {
        String name = nameField.getText();
        String email = emailField.getText();
        try {
            userService.createUser(name, email);
            closeModal();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    @FXML
    private TextField idField;

    @FXML
    private void handleSaveUser() {
        String idText = idField.getText();
        String name = nameField.getText();
        String email = emailField.getText();

        try {
            if (idText == null || idText.isBlank()) {
                // Add new user
                userService.createUser(name, email);
            } else {
                // Edit existing user
                int id = Integer.parseInt(idText);
                userService.updateUser(id, name, email);
            }
            closeModal();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void setUser(User user) {
        if (user != null) {
            idField.setText(String.valueOf(user.getId()));
            nameField.setText(user.getName());
            emailField.setText(user.getEmail());
        }
    }

    private void closeModal() {
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }
}
