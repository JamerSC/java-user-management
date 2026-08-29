package org.example.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.dao.UserDAO;
import org.example.model.User;
import org.example.service.UserService;

import java.util.List;

public class UserController {


    @FXML
    private TableView<User> tableView;

    @FXML
    private TableColumn<User, Integer> idColumn;

    @FXML
    private TableColumn<User, String> nameColumn;

    @FXML
    private TableColumn<User, String> emailColumn;

    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    private final UserService userService = new UserService(new UserDAO());

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        loadUsers();
    }

    public void loadUsers() {
        List<User> users = userService.getAllUsers();
        tableView.setItems(FXCollections.observableArrayList(users));
    }

    public void addUser() {
        String name = nameField.getText();
        String email = emailField.getText();
        userService.createUser(name, email);
        loadUsers();
    }

    public void updateUser() {
        User selectedUser = tableView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            userService.updateUser(selectedUser.getId(), nameField.getText(), emailField.getText());
            loadUsers();
        }
    }

    public void deleteUser() {
        User selectedUser = tableView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            userService.deleteUserById(selectedUser.getId());
            loadUsers();
        }
    }
}
