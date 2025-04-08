package com.visionaryann.production.usermanagement.controllers;
import com.visionaryann.production.usermanagement.ApiClient;
import com.visionaryann.production.usermanagement.payload.*;
import com.visionaryann.production.usermanagement.model.User;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

public class ProfileController {
    @FXML Label lblUsername, lblEmail, message;
    @FXML TextField newEmail;
    @FXML PasswordField newPassword;

    @FXML public void initialize() {
        try {
            User u = ApiClient.get("/users/profile", User.class);
            lblUsername.setText(u.getUsername());
            lblEmail.setText(u.getEmail());
        } catch (Exception e) {
            message.setText("Failed to load profile");
        }
    }

    public void handleUpdate() {
        try {
            User u = ApiClient.put("/users/profile",
                    new UpdateProfileRequest(newEmail.getText(), newPassword.getText()),
                    User.class);
            lblEmail.setText(u.getEmail());
            message.setText("Profile updated");
        } catch (Exception e) {
            message.setText("Update failed");
        }
    }

    public void goToAbout() { loadScene("about.fxml"); }

    private void loadScene(String fxml) {
        try {
            Stage stage = (Stage) lblUsername.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/" + fxml));
            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
            stage.setScene(scene);
        } catch (Exception ignored) {}
    }
}
