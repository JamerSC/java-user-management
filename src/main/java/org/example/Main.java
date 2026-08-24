package org.example;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.dao.UserDAO;
import org.example.database.DatabaseConnection;
import org.example.model.User;
import org.example.service.UserService;

import java.sql.Connection;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
//        stage.setTitle("JavaFX Application");
//        stage.setTitle("User Management System");
//        stage.show();

        stage.setTitle("User Management System");

        // 1. Create Service
        UserService userService =
                new UserService(new org.example.dao.UserDAO());

        // 2. Get users from database
        List<User> users =
                userService.getAllUsers();

        // 3. Create TableView
        TableView<User> tableView =
                new TableView<>();

        // 4. ID Column
        TableColumn<User, Integer> idColumn =
                new TableColumn<>("ID");

        idColumn.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );

        // 5. Name Column
        TableColumn<User, String> nameColumn =
                new TableColumn<>("Name");

        nameColumn.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );

        // 6. Email Column
        TableColumn<User, String> emailColumn =
                new TableColumn<>("Email");

        emailColumn.setCellValueFactory(
                new PropertyValueFactory<>("email")
        );

        // 7. Add columns
        tableView.getColumns().addAll(
                idColumn,
                nameColumn,
                emailColumn
        );

        // 8. Add database data to table
        tableView.setItems(
                FXCollections.observableArrayList(users)
        );

        // 9. Create Scene
        Scene scene = new Scene(
                tableView,
                600,
                400
        );

        // 10. Set Scene
        stage.setScene(scene);

        // 11. Show window
        stage.show();
    }

//     Main class variables
//    String name = "Jane Doe";

    public static void main(String[] args) {

        launch(args);

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

//     Main class methods
    public void makeItLowerCase(String x) {
        System.out.println(x.toLowerCase());
    }

//    public String makeItLowerCase() {
//        return name.toLowerCase();
//    }

    public static void makeItUpperCase(String lastName) {
        System.out.println(lastName.toUpperCase());
    }
}



//        Main myObject = new Main();
//        System.out.println(myObject.name);
//        makeItUpperCase(myObject.name);
//        myObject.makeItLowerCase(myObject.name);
//        System.out.println(myObject.makeItLowerCase());

//        Person person1 = new Person("John", "Doe", 'M', 30);
//        Person person2 = new Person("Mary", "Public", 'F', 17);
//        System.out.println(person1.toString());

//        Person person3 = new Person("Alice", "Smith", 'F', 25);
//        person3.setLastName("Johnson");
//        makeItUpperCase(person3.getLastName());
//        person3.greet();
//        System.out.println(person3.getLastName());
//        System.out.println(person3.getFullName());