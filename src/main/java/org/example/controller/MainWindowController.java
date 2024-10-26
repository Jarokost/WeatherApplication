package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.model.Weather;
import org.example.model.CurrentWeatherAndForecast;
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
        CurrentWeatherAndForecast currentWeatherAndForecast = weatherServiceLeft.getWeather(cityLeft.getText() + "," + countryLeft.getText());

        displayWeather(currentWeatherAndForecast, groupLeftList.iterator());
    }

    @FXML
    void clickButtonRefreshRight(ActionEvent event) {
        CurrentWeatherAndForecast currentWeatherAndForecast = weatherServiceRight.getWeather(cityRight.getText() + "," + countryRight.getText());

        displayWeather(currentWeatherAndForecast, groupRightList.iterator());
    }

    private void displayWeather(CurrentWeatherAndForecast currentWeatherAndForecast, Iterator<Group> groupIterator) {
        // filling weather forecast group fields
        Iterator<Weather> weatherIterator = currentWeatherAndForecast.getWeathers().iterator();
        while (groupIterator.hasNext() && weatherIterator.hasNext()) {
            Group group = groupIterator.next();
            Weather weather = weatherIterator.next();
            Label labelDay = (Label) group.getChildren().get(1);
            Label labelTemp = (Label) group.getChildren().get(2);
            labelDay.setText(weather.getDate().toString());
            labelTemp.setText(String.valueOf(weather.getTempInCelsius()));
        }
        //fill current weather group fields
        Group group = groupIterator.next();
        Label labelDay = (Label) group.getChildren().get(1);
        Label labelTemp = (Label) group.getChildren().get(2);
        labelDay.setText(currentWeatherAndForecast.getCurrentWeather().getDate().toString());
        labelTemp.setText(String.valueOf(currentWeatherAndForecast.getCurrentWeather().getTempInCelsius()));
    }


}
