module WeatherApplication {
    requires javafx.fxml;
    requires javafx.controls;
    requires org.apache.httpcomponents.client5.httpclient5;
    requires org.apache.httpcomponents.core5.httpcore5;
    requires org.json;

    opens org.example;
    opens org.example.controller;
    opens org.example.view;
}