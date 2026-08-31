package org.example.ui;

import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.controller.AddUserModalController;
import org.example.model.User;
import org.example.service.UserService;


import java.util.List;

public class UserManagementUI {


    private final UserService userService = new UserService(new org.example.dao.UserDAO());
    private final TableView<User> tableView = new TableView<>();
//    private final TextField nameField = new TextField();
//    private final TextField emailField = new TextField();
    private final VBox root;

    public UserManagementUI() {
        // Initialize UI components

        TableColumn<User, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<User, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<User, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));


        tableView.getColumns().addAll(idColumn, nameColumn, emailColumn);

        // Initialize action column
        initializeActionColumn();

        Button addButton = new Button("Add User");
        addButton.setOnAction(event -> openAddUserModal());
        HBox buttonBox = new HBox(10, addButton);

        root = new VBox(10, buttonBox, tableView);

        loadUsers();
    }

    public VBox getRoot() {
        return root;
    }

    private void loadUsers() {
        List<User> users = userService.getAllUsers();
        tableView.setItems(FXCollections.observableArrayList(users));
    }

    private void openAddUserModal() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddUserModal.fxml"));
            VBox modalRoot = loader.load();

            AddUserModalController controller = loader.getController();
            controller.setUserService(userService);

            Stage modalStage = new Stage();
            modalStage.setTitle("Add New User");
            modalStage.setScene(new Scene(modalRoot));
            modalStage.showAndWait();

            loadUsers(); // Refresh the table after adding a user
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initializeActionColumn() {
        TableColumn<User, Void> actionColumn = new TableColumn<>("Actions");

        actionColumn.setCellFactory(param -> new TableCell<>() {
            private final Button editButton = new Button("Edit");
            private final Button deleteButton = new Button("Delete");
            private final HBox actionButtons = new HBox(10, editButton, deleteButton);

            {
                editButton.setOnAction(event -> {
                    User user = getTableView().getItems().get(getIndex());
                    openEditUserModal(user);
                });

                deleteButton.setOnAction(event -> {
                    User user = getTableView().getItems().get(getIndex());
                    deleteUser(user);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(actionButtons);
                }
            }
        });

        tableView.getColumns().add(actionColumn);
    }

    private void openEditUserModal(User user) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddUserModal.fxml"));
            VBox modalRoot = loader.load();

            AddUserModalController controller = loader.getController();
            controller.setUserService(userService);
            controller.setUser(user);

            Stage modalStage = new Stage();
            modalStage.setTitle("Edit User");
            modalStage.setScene(new Scene(modalRoot));
            modalStage.showAndWait();

            loadUsers(); // Refresh the table after editing a user
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteUser(User user) {
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Delete User");
        confirmation.setHeaderText("Delete selected user?");
        confirmation.setContentText("User: " + user.getName());
        if (confirmation.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
            try {
                userService.deleteUserById(user.getId());
                showMessage("Success", "User deleted successfully!");
                loadUsers();
            } catch (Exception e) {
                showMessage("Error", e.getMessage());
            }
        }
    }

    private void openEditUserModal() {
        User selectedUser = tableView.getSelectionModel().getSelectedItem();
        if (selectedUser == null) {
            showMessage("Warning", "Please select a user to edit.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddUserModal.fxml"));
            VBox modalRoot = loader.load();

            AddUserModalController controller = loader.getController();
            controller.setUserService(userService);
            controller.setUser(selectedUser);

            Stage modalStage = new Stage();
            modalStage.setTitle("Edit User");
            modalStage.setScene(new Scene(modalRoot));
            modalStage.showAndWait();

            loadUsers(); // Refresh the table after editing a user
        } catch (Exception e) {
            e.printStackTrace();
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
//                clearFields();
                loadUsers();
            } catch (Exception e) {
                showMessage("Error", e.getMessage());
            }
        }
    }

    private void showMessage(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
