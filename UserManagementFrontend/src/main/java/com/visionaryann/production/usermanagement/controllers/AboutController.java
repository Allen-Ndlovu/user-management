package com.visionaryann.production.usermanagement.controllers;
import javafx.fxml.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

public class AboutController {
    public void goToProfile() {
        try {
            Stage stage = (Stage) FXMLLoader.load(getClass().getResource("/about.fxml")).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/profile.fxml"));
            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
            stage.setScene(scene);
        } catch (Exception ignored) {}
    }
}
