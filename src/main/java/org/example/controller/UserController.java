package org.example.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.dao.UserDAO;
import org.example.dto.UserDto;
import org.example.mapper.UserMapper;
import org.example.model.User;
import org.example.service.UserService;

import java.util.List;

public class UserController {


    @FXML
    private TableView<UserDto> tableView;

    @FXML
    private TableColumn<UserDto, Integer> idColumn;

    @FXML
    private TableColumn<UserDto, String> nameColumn;

    @FXML
    private TableColumn<UserDto, String> emailColumn;

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
        List<UserDto> userDtos = users.stream()
                                        .map(UserMapper::toDto)
                .toList();
        tableView.setItems(FXCollections.observableArrayList(userDtos));
    }

    public void addUser() {
        String name = nameField.getText();
        String email = emailField.getText();
        userService.createUser(name, email);
        loadUsers();
    }

    public void updateUser() {
        UserDto selectedUser = tableView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            userService.updateUser(selectedUser.getId(), nameField.getText(), emailField.getText());
            loadUsers();
        }
    }

    public void deleteUser() {
        UserDto selectedUser = tableView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            userService.deleteUserById(selectedUser.getId());
            loadUsers();
        }
    }
}
