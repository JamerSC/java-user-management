package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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

    private void closeModal() {
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }
}
