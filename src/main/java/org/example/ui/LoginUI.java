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

        // Login screens usually look best small (e.g., $400 \times 300$),
        // while management screens with data tables need much more space
        // (e.g., $1000 \times 700$).

        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            if (authService.login(username, password)) {
                UserManagementUI userManagementUI = new UserManagementUI(primaryStage);
                primaryStage.getScene().setRoot(userManagementUI.getRoot());
                primaryStage.setTitle("User Management System");

                // --- Resize Stage for the Main App View ---
                primaryStage.setWidth(1000);  // Set new width
                primaryStage.setHeight(700);  // Set new height
                primaryStage.centerOnScreen(); // Recenter window
            } else {
                errorLabel.setText("Invalid credentials!");
            }
        });
    }

    public VBox getRoot() {
        return root;
    }
}