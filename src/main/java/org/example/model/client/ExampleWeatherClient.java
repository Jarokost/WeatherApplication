package org.example.model.client;

import org.example.model.SingleDayWeather;
import org.example.model.WeatherForecast;

import java.util.Collection;
import java.util.List;

public class ExampleWeatherClient implements WeatherClient {
    @Override
    public WeatherForecast getWeather(String cityName) {
        Collection<SingleDayWeather> weathers = List.of();
        return new WeatherForecast("Poznań", weathers);
    }
}
