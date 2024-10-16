package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.view.ViewFactory;

public class MainWindowController extends BaseController {

    @FXML
    private Button buttonRefreshLeft;

    @FXML
    private Button buttonRefreshRight;

    @FXML
    private TextField cityLeft;

    @FXML
    private TextField cityRight;

    @FXML
    private TextField countryLeft;

    @FXML
    private TextField countryRight;

    @FXML
    private Label labelLeftDay1;

    @FXML
    private Label labelLeftDay2;

    @FXML
    private Label labelLeftDay3;

    @FXML
    private Label labelLeftDay4;

    @FXML
    private Label labelLeftDay5;

    @FXML
    private Label labelLeftTemp1;

    @FXML
    private Label labelLeftTemp2;

    @FXML
    private Label labelLeftTemp3;

    @FXML
    private Label labelLeftTemp4;

    @FXML
    private Label labelLeftTemp5;

    @FXML
    private Label labelRightDay1;

    @FXML
    private Label labelRightDay2;

    @FXML
    private Label labelRightDay3;

    @FXML
    private Label labelRightDay4;

    @FXML
    private Label labelRightDay5;

    @FXML
    private Label labelRightTemp1;

    @FXML
    private Label labelRightTemp2;

    @FXML
    private Label labelRightTemp3;

    @FXML
    private Label labelRightTemp4;

    @FXML
    private Label labelRightTemp5;

    public MainWindowController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    @FXML
    void clickButtonRefreshLeft(ActionEvent event) {

    }

    @FXML
    void clickButtonRefreshRight(ActionEvent event) {

    }
}
