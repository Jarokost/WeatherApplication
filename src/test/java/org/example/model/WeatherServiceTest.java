package org.example.model;

import org.example.model.client.WeatherClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeatherServiceTest {

    @InjectMocks
    private WeatherService weatherService;
    @Mock
    private WeatherClient weatherClient;

    @Test
    void shouldReturnWeatherForecast() {
        //given
        LocalDate date = LocalDate.now();
        Weather expectedCurrentWeather = createWeather(date);
        List<Weather> expectedForecastWeather = List.of(createWeather(date.plusDays(1)), createWeather(date.plusDays(2)));
        when(weatherClient.currentWeather("Poznan"))
                .thenReturn(expectedCurrentWeather);
        when(weatherClient.forecast("Poznan"))
                .thenReturn(expectedForecastWeather);

        //when
        CurrentWeatherAndForecast result = weatherService.getWeather("Poznan");

        //then
        assertEquals(result.getCurrentWeather(), expectedCurrentWeather);
        assertEquals(result.getWeathers(), expectedForecastWeather);
    }

    @Test
    void shouldThrowFailedToGetWeatherExceptionWhenCannotGetWeather() {
        //given
        when(weatherClient.currentWeather("City")).thenThrow(FailedToGetWeatherException.class);

        //when then
        assertThrows(FailedToGetWeatherException.class, () -> weatherClient.currentWeather("City"));
    }

    private Weather createWeather(LocalDate date) {
        return new Weather(12, date);
    }
}