package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.model.SingleDayWeather;
import org.example.model.WeatherForecast;
import org.example.model.WeatherService;
import org.example.model.WeatherServiceFactory;
import org.example.view.ViewFactory;

import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

public class MainWindowController extends BaseController implements Initializable {

    @FXML
    private TextField cityLeft;

    @FXML
    private TextField cityRight;

    @FXML
    private TextField countryLeft;

    @FXML
    private TextField countryRight;

    @FXML
    private List<Group> groupLeftList;

    @FXML
    private List<Group> groupRightList;

    private WeatherService weatherServiceLeft;
    private WeatherService weatherServiceRight;

    public MainWindowController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        countryLeft.setText("Polska");
        cityLeft.setText("Poznan");
        countryRight.setText("Hiszpania");
        cityRight.setText("Santa Cruz De Tenerife");

        weatherServiceLeft = WeatherServiceFactory.createWeatherService();
        weatherServiceRight = WeatherServiceFactory.createWeatherService();
    }

    @FXML
    void clickButtonRefreshLeft(ActionEvent event) {
        WeatherForecast weatherForecast = weatherServiceLeft.getWeather(cityLeft.getText() + "," + countryLeft.getText());

        displayWeatherLeft(weatherForecast);
    }

    @FXML
    void clickButtonRefreshRight(ActionEvent event) {
        WeatherForecast weatherForecast = weatherServiceRight.getWeather(cityRight.getText() + "," + countryRight.getText());

        displayWeatherRight(weatherForecast);
    }

    private void displayWeatherLeft(WeatherForecast weatherForecast) {
        Iterator<Group> groupIterator = groupLeftList.iterator();
        Iterator<SingleDayWeather> weatherIterator = weatherForecast.getWeathers().iterator();
        while (groupIterator.hasNext() && weatherIterator.hasNext()) {
            Group group = groupIterator.next();
            SingleDayWeather singleDayWeather = weatherIterator.next();
            Label labelDay = (Label) group.getChildren().get(1);
            Label labelTemp = (Label) group.getChildren().get(2);
            labelDay.setText(singleDayWeather.getDate().toString());
            labelTemp.setText(String.valueOf(singleDayWeather.getTempInCelsius()));
        }
    }

    private void displayWeatherRight(WeatherForecast weatherForecast) {
        Iterator<Group> groupIterator = groupRightList.iterator();
        Iterator<SingleDayWeather> weatherIterator = weatherForecast.getWeathers().iterator();
        while (groupIterator.hasNext() && weatherIterator.hasNext()) {
            Group group = groupIterator.next();
            SingleDayWeather singleDayWeather = weatherIterator.next();
            Label labelDay = (Label) group.getChildren().get(1);
            Label labelTemp = (Label) group.getChildren().get(2);
            labelDay.setText(singleDayWeather.getDate().toString());
            labelTemp.setText(String.valueOf(singleDayWeather.getTempInCelsius()));
        }
    }


}
