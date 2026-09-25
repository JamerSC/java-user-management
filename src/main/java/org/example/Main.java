package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.ui.LoginUI;
import org.example.ui.UserManagementUI;

public class Main extends Application {

//    @Override
//    public void start(Stage stage) {
//        UserManagementUI userManagementUI = new UserManagementUI();
//        Scene scene = new Scene(userManagementUI.getRoot(), 700, 500);
//        stage.setTitle("User Management");
//        stage.setScene(scene);
//        stage.show();
//    }

    @Override
    public void start(Stage primaryStage) {
        LoginUI loginUI = new LoginUI(primaryStage);

        Scene scene = new Scene(loginUI.getRoot());
        primaryStage.setTitle("System Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}