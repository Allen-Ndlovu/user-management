module com.visionaryann.production.usermanagement {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.visionaryann.production.usermanagement to javafx.fxml;
    exports com.visionaryann.production.usermanagement;
}