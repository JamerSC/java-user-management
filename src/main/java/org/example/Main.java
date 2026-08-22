package org.example;

import org.example.database.DatabaseConnection;
import org.example.model.User;
import org.example.service.UserService;

import java.sql.Connection;
import java.util.List;

public class Main {

//     Main class variables
//    String name = "Jane Doe";

    public static void main(String[] args) {

        UserService userService = new UserService(new org.example.dao.UserDAO());

//        ✅ CREATE USER
//        userService.createUser(
//                "Peter Smith",
//                "petersmith@example.com"
//        );

//        ✅ READ ALL
        List<User> users = userService.getAllUsers();

//        for (User user : users) {
//            System.out.println(user);
//        }

        // java 8
        // Approach A: Using a lambda expression
//        users.forEach(user -> System.out.println(user));

        // Approach B: Using a compact method reference
        users.forEach(System.out::println);


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