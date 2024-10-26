package org.example.model.client;

import org.example.model.Weather;

import java.util.List;

public interface WeatherClient {

    Weather currentWeather(String cityName);

    List<Weather> forecast(String cityName);
}
