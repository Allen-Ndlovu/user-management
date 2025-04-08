package com.visionaryann.production.usermanagement.controllers;
import com.visionaryann.production.usermanagement.ApiClient;
import com.visionaryann.production.usermanagement.payload.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

public class LoginController {
    @FXML TextField username;
    @FXML PasswordField password;
    @FXML Label message;

    public void handleLogin() {
        try {
            AuthResponse resp = ApiClient.post("/auth/login",
                    new LoginRequest(username.getText(), password.getText()),
                    AuthResponse.class);
            loadScene("profile.fxml");
        } catch (Exception e) {
            message.setText("Login failed");
        }
    }
    public void goToRegister() { loadScene("register.fxml"); }

    private void loadScene(String fxml) {
        try {
            Stage stage = (Stage) username.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/" + fxml));
            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
            stage.setScene(scene);
        } catch (Exception ignored) {}
    }
}
