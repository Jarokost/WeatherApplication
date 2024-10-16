package org.example.model.client;

import org.example.model.WeatherForecast;

public interface WeatherClient {
    WeatherForecast getWeather(String cityName);
}
