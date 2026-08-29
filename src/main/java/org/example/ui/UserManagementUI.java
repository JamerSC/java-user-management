package org.example.ui;

import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.model.User;
import org.example.service.UserService;


import java.util.List;

public class UserManagementUI {


    private final UserService userService = new UserService(new org.example.dao.UserDAO());
    private final TableView<User> tableView = new TableView<>();
    private final TextField nameField = new TextField();
    private final TextField emailField = new TextField();
    private final VBox root;

    public UserManagementUI() {
        // Initialize UI components
        nameField.setPromptText("Name");
        emailField.setPromptText("Email");

        TableColumn<User, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<User, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<User, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        tableView.getColumns().addAll(idColumn, nameColumn, emailColumn);

        Button addButton = new Button("Add User");
        Button updateButton = new Button("Update");
        Button deleteButton = new Button("Delete");

        addButton.setOnAction(event -> addUser());
        updateButton.setOnAction(event -> updateUser());
        deleteButton.setOnAction(event -> deleteUser());

        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldUser, selectedUser) -> {
            if (selectedUser != null) {
                nameField.setText(selectedUser.getName());
                emailField.setText(selectedUser.getEmail());
            }
        });

        HBox inputBox = new HBox(10, nameField, emailField);
        HBox buttonBox = new HBox(10, addButton, updateButton, deleteButton);

        root = new VBox(10, inputBox, buttonBox, tableView);

        loadUsers();
    }

    public VBox getRoot() {
        return root;
    }

    private void loadUsers() {
        List<User> users = userService.getAllUsers();
        tableView.setItems(FXCollections.observableArrayList(users));
    }

    private void addUser() {
        String name = nameField.getText();
        String email = emailField.getText();
        try {
            userService.createUser(name, email);
            showMessage("Success", "User created successfully!");
            clearFields();
            loadUsers();
        } catch (Exception e) {
            showMessage("Error", e.getMessage());
        }
    }

    private void updateUser() {
        User selectedUser = tableView.getSelectionModel().getSelectedItem();
        if (selectedUser == null) {
            showMessage("Warning", "Please select a user.");
            return;
        }
        try {
            userService.updateUser(selectedUser.getId(), nameField.getText(), emailField.getText());
            showMessage("Success", "User updated successfully!");
            clearFields();
            loadUsers();
        } catch (Exception e) {
            showMessage("Error", e.getMessage());
        }
    }

    private void deleteUser() {
        User selectedUser = tableView.getSelectionModel().getSelectedItem();
        if (selectedUser == null) {
            showMessage("Warning", "Please select a user.");
            return;
        }
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Delete User");
        confirmation.setHeaderText("Delete selected user?");
        confirmation.setContentText("User: " + selectedUser.getName());
        if (confirmation.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
            try {
                userService.deleteUserById(selectedUser.getId());
                showMessage("Success", "User deleted successfully!");
                clearFields();
                loadUsers();
            } catch (Exception e) {
                showMessage("Error", e.getMessage());
            }
        }
    }

    private void clearFields() {
        nameField.clear();
        emailField.clear();
        tableView.getSelectionModel().clearSelection();
    }

    private void showMessage(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
