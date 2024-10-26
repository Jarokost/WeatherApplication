package org.example.model;

import org.example.model.client.WeatherClient;

import java.util.List;

public class WeatherService {

    private final WeatherClient weatherClient;

    public WeatherService(WeatherClient weatherClient) {
        this.weatherClient = weatherClient;
    }

    public CurrentWeatherAndForecast getWeather(String cityName) {
        try {
            Weather currentWeather = weatherClient.currentWeather(cityName);
            List<Weather> weatherForecast = weatherClient.forecast(cityName);
            return new CurrentWeatherAndForecast(currentWeather, weatherForecast);
        } catch (Exception e) {
            throw new FailedToGetWeatherException("Failed to get weather", e);
        }
    }
}
