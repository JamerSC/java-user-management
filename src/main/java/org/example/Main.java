package org.example;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.dao.UserDAO;
import org.example.database.DatabaseConnection;
import org.example.model.User;
import org.example.service.UserService;

import java.sql.Connection;
import java.util.List;

public class Main extends Application {

    private final UserService userService =
            new UserService(new UserDAO());

    private final TableView<User> tableView =
            new TableView<>();

    private final TextField nameField =
            new TextField();

    private final TextField emailField =
            new TextField();


    @Override
    public void start(Stage stage) {

        stage.setTitle("User Management System");


        // ==========================================
        // TABLE
        // ==========================================

        TableColumn<User, Integer> idColumn =
                new TableColumn<>("ID");

        idColumn.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );


        TableColumn<User, String> nameColumn =
                new TableColumn<>("Name");

        nameColumn.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );


        TableColumn<User, String> emailColumn =
                new TableColumn<>("Email");

        emailColumn.setCellValueFactory(
                new PropertyValueFactory<>("email")
        );


        tableView.getColumns().addAll(
                idColumn,
                nameColumn,
                emailColumn
        );


        // ==========================================
        // INPUT FIELDS
        // ==========================================

        nameField.setPromptText("Name");
        emailField.setPromptText("Email");


        // ==========================================
        // BUTTONS
        // ==========================================

        Button addButton =
                new Button("Add User");

        Button updateButton =
                new Button("Update");

        Button deleteButton =
                new Button("Delete");


        // ==========================================
        // BUTTON ACTIONS
        // ==========================================

        addButton.setOnAction(event -> addUser());

        updateButton.setOnAction(event -> updateUser());

        deleteButton.setOnAction(event -> deleteUser());


        // ==========================================
        // TABLE SELECTION
        // ==========================================

        tableView.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldUser, selectedUser) -> {

                    if (selectedUser != null) {

                        nameField.setText(
                                selectedUser.getName()
                        );

                        emailField.setText(
                                selectedUser.getEmail()
                        );
                    }
                });


        // ==========================================
        // LAYOUT
        // ==========================================

        HBox inputBox =
                new HBox(
                        10,
                        nameField,
                        emailField
                );


        HBox buttonBox =
                new HBox(
                        10,
                        addButton,
                        updateButton,
                        deleteButton
                );


        VBox root =
                new VBox(
                        10,
                        inputBox,
                        buttonBox,
                        tableView
                );


        // ==========================================
        // SCENE
        // ==========================================

        Scene scene =
                new Scene(root, 700, 500);

        stage.setScene(scene);

        stage.show();


        // ==========================================
        // LOAD USERS
        // ==========================================

        loadUsers();
    }


    // ==========================================
    // READ
    // ==========================================

    private void loadUsers() {

        List<User> users =
                userService.getAllUsers();

        tableView.setItems(
                FXCollections.observableArrayList(users)
        );
    }


    // ==========================================
    // CREATE
    // ==========================================

    private void addUser() {

        String name =
                nameField.getText();

        String email =
                emailField.getText();


        try {

            userService.createUser(
                    name,
                    email
            );


            showMessage(
                    "Success",
                    "User created successfully!"
            );


            clearFields();

            loadUsers();


        } catch (Exception e) {

            showMessage(
                    "Error",
                    e.getMessage()
            );
        }
    }


    // ==========================================
    // UPDATE
    // ==========================================

    private void updateUser() {

        User selectedUser =
                tableView.getSelectionModel()
                        .getSelectedItem();


        if (selectedUser == null) {

            showMessage(
                    "Warning",
                    "Please select a user."
            );

            return;
        }


        String name =
                nameField.getText();

        String email =
                emailField.getText();


        try {

            userService.updateUser(
                    selectedUser.getId(),
                    name,
                    email
            );


            showMessage(
                    "Success",
                    "User updated successfully!"
            );


            clearFields();

            loadUsers();


        } catch (Exception e) {

            showMessage(
                    "Error",
                    e.getMessage()
            );
        }
    }


    // ==========================================
    // DELETE
    // ==========================================

    private void deleteUser() {

        User selectedUser =
                tableView.getSelectionModel()
                        .getSelectedItem();


        if (selectedUser == null) {

            showMessage(
                    "Warning",
                    "Please select a user."
            );

            return;
        }


        Alert confirmation =
                new Alert(
                        Alert.AlertType.CONFIRMATION
                );


        confirmation.setTitle(
                "Delete User"
        );

        confirmation.setHeaderText(
                "Delete selected user?"
        );

        confirmation.setContentText(
                "User: "
                        + selectedUser.getName()
        );


        if (
                confirmation.showAndWait()
                        .orElse(ButtonType.CANCEL)
                        == ButtonType.OK
        ) {

            try {

                userService.deleteUserById(
                        selectedUser.getId()
                );


                showMessage(
                        "Success",
                        "User deleted successfully!"
                );


                clearFields();

                loadUsers();


            } catch (Exception e) {

                showMessage(
                        "Error",
                        e.getMessage()
                );
            }
        }
    }


    // ==========================================
    // CLEAR FORM
    // ==========================================

    private void clearFields() {

        nameField.clear();

        emailField.clear();

        tableView
                .getSelectionModel()
                .clearSelection();
    }


    // ==========================================
    // MESSAGE
    // ==========================================

    private void showMessage(
            String title,
            String message
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle(title);

        alert.setHeaderText(null);

        alert.setContentText(message);

        alert.showAndWait();
    }


    public static void main(String[] args) {

        launch(args);
    }
//        UserService userService = new UserService(new org.example.dao.UserDAO());

//        ✅ CREATE USER
//        userService.createUser(
//                "Peter Smith",
//                "petersmith@example.com"
//        );

//        ✅ READ ALL
//        List<User> users = userService.getAllUsers();
//
//        for (User user : users) {
//            System.out.println(user);
//        }

        // java 8
        // Approach A: Using a lambda expression
//        users.forEach(user -> System.out.println(user));

        // Approach B: Using a compact method reference
//        users.forEach(System.out::println);


//      ✅ FIND ONE
//        User user = userService.getUserById(1);
//        if (user == null) {
//            System.out.println("User not found");
//        } else {
//            System.out.println("Found");
//            System.out.println("User: " + user);
//        }

//        ✅ UPDATE USER
//                userService.updateUser(
//                        3,
//                "Peter Smith II",
//                "petersmith@example.com"
//        );

//        ✅ DELETE USER
//        userService.deleteUserById(3);


//       ✅ database test connection
//        try {
//            Connection connection = DatabaseConnection.getConnection();
//
//            System.out.println("Connected to MySQL");
//
//            connection.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

}