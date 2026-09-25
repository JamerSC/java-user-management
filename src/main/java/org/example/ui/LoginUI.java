package org.example.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.dao.UserDAO;
import org.example.service.AuthService;
import org.example.service.UserService;

public class LoginUI {

    private final VBox root;
    private final AuthService authService = new AuthService(new UserService(new UserDAO()));

    public LoginUI(Stage primaryStage) {
        Label titleLabel = new Label("System Login");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username / Email (e.g. admin or user email)");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password (admin123 or 123456)");

        Label errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red;");

        Button loginButton = new Button("Login");
        loginButton.setDefaultButton(true);

        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            if (authService.login(username, password)) {
                // Login successful: navigate to UserManagementUI
                UserManagementUI userManagementUI = new UserManagementUI(primaryStage);
                primaryStage.getScene().setRoot(userManagementUI.getRoot());
                primaryStage.setTitle("User Management System");
            } else {
                errorLabel.setText("Invalid credentials! Use admin/admin123");
            }
        });

        root = new VBox(12, titleLabel, usernameField, passwordField, loginButton, errorLabel);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30));
        root.setPrefSize(380, 260);
    }

    public VBox getRoot() {
        return root;
    }
}