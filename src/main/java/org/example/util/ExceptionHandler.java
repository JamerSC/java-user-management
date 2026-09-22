package org.example.util;

import javafx.scene.control.Alert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    public static void handleException(Exception e, String userMessage) {
        // Log the exception
        logger.error("An error occurred: ", e);

        // Show an alert to the user
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("An unexpected error occurred");
        alert.setContentText(userMessage);
        alert.showAndWait();
    }
}
