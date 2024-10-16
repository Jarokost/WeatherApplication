module WeatherApplication {
    requires javafx.fxml;
    requires javafx.controls;

    opens org.example;
    opens org.example.controller;
    opens org.example.view;
}