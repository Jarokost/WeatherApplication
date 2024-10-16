package org.example.model;

import org.example.model.client.WeatherClient;

public class WeatherService {

    private final WeatherClient weatherClient;

    public WeatherService(WeatherClient weatherClient) {
        this.weatherClient = weatherClient;
    }

    public WeatherForecast getWeather(String cityName) {
        return weatherClient.getWeather(cityName);
    }
}
