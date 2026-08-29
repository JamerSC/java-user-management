package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.ui.UserManagementUI;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        UserManagementUI userManagementUI = new UserManagementUI();
        Scene scene = new Scene(userManagementUI.getRoot(), 700, 500);
        stage.setTitle("User Management System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}