package com.visionaryann.production.usermanagement;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import java.io.FileInputStream;
import java.util.Properties;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStream;

public class LoginController {
    @FXML private TextField usernameField;
    @FXML private TextField passwordField;
    @FXML private Label statusLabel;

    private String backendApiUrl;

    public void initialize() {
        try {
            Properties props = new Properties();
            props.load(new FileInputStream("config.properties"));
            backendApiUrl = props.getProperty("backend.api.url");
        } catch (Exception e) {
            statusLabel.setText("Error loading config: " + e.getMessage());
        }
    }

    @FXML
    public void login() {
        // Dummy login implementation calling backend API
        try {
            URL url = new URL(backendApiUrl + "/login");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            String payload = "{\"username\":\"" + usernameField.getText() + "\", \"password\":\"" + passwordField.getText() + "\"}";
            conn.setRequestProperty("Content-Type", "application/json");
            OutputStream os = conn.getOutputStream();
            os.write(payload.getBytes());
            os.flush();
            if (conn.getResponseCode() == 200) {
                statusLabel.setText("Login successful!");
            } else {
                statusLabel.setText("Login failed!");
            }
            conn.disconnect();
        } catch (Exception e) {
            statusLabel.setText("Error: " + e.getMessage());
        }
    }
}
