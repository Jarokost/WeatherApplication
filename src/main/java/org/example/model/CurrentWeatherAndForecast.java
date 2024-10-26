package org.example.model;

import java.time.LocalDate;
import java.util.List;

public class CurrentWeatherAndForecast {

    private final Weather currentWeather;
    private final List<Weather> weathers;

    public CurrentWeatherAndForecast(Weather currentWeather, List<Weather> weathers) {
        this.currentWeather = currentWeather;
        this.weathers = weathers;
    }

    public Weather getCurrentWeather() {
        return currentWeather;
    }

    public List<Weather> getWeathers() {
        return weathers;
    }
}
