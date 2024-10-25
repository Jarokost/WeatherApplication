package org.example.model.client;

import org.example.model.SingleDayWeather;
import org.example.model.WeatherForecast;

import java.util.Collection;

public interface WeatherClient {

    SingleDayWeather currentWeather(String cityName);

    Collection<SingleDayWeather> forecast(String cityName);
}
