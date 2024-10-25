package org.example.model.client;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import org.example.model.SingleDayWeather;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TimeZone;


public class OpenWeatherAPIWeatherClient implements WeatherClient {


    @Override
    public SingleDayWeather currentWeather(String cityName) {
        SingleDayWeather singleDayWeather;

        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {

            String url = "https://api.openweathermap.org/data/2.5/weather?" +
                    "q=" + cityNameToNoWhiteSpaces(cityName) +
                    "&appid=" + APIKey.getApiKey() +
                    "&units=" + "metric";
            ClassicHttpRequest httpGet = ClassicRequestBuilder.get(url).build();

            singleDayWeather = httpclient.execute(httpGet, response -> {
                System.out.println("GET: " + response.getCode() + " " + response.getReasonPhrase());
                final HttpEntity entity = response.getEntity();

                String retSrc = EntityUtils.toString(entity);

                JSONObject result = new JSONObject(retSrc);
                int dt = result.getInt("dt");
                LocalDate date = LocalDate.ofInstant(Instant.ofEpochSecond(dt),
                        TimeZone.getTimeZone("GMT+0").toZoneId());
                JSONObject main = result.getJSONObject("main");
                BigDecimal temp = main.getBigDecimal("temp");

                EntityUtils.consume(entity);
                return new SingleDayWeather(temp.doubleValue(), date);
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return singleDayWeather;
    }

    @Override
    public Collection<SingleDayWeather> forecast(String cityName) {
        Collection<SingleDayWeather> forecast = new ArrayList<>();

        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {

            String url = "https://api.openweathermap.org/data/2.5/forecast?" +
                    "q=" + cityNameToNoWhiteSpaces(cityName) +
                    "&appid=" + APIKey.getApiKey() +
                    "&units=" + "metric";
            ClassicHttpRequest httpGet = ClassicRequestBuilder.get(url).build();


            forecast = httpclient.execute(httpGet, response -> {
                System.out.println("GET: " + response.getCode() + " " + response.getReasonPhrase());
                final HttpEntity entity = response.getEntity();

                String retSrc = EntityUtils.toString(entity);

                JSONObject result = new JSONObject(retSrc);
                JSONArray tokenList = result.getJSONArray("list");

                Collection<SingleDayWeather> forecastCollection = new ArrayList<>();
                for(int i = 0, j = 0; i<40; i++) {
                    JSONObject singleDayObject = tokenList.getJSONObject(i);
                    Integer dt = singleDayObject.getInt("dt");
                    LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(dt),
                            TimeZone.getTimeZone("GMT+0").toZoneId());
                    LocalDate date = LocalDate.ofInstant(Instant.ofEpochSecond(dt),
                            TimeZone.getTimeZone("GMT+0").toZoneId());
                    JSONObject main = singleDayObject.getJSONObject("main");
                    BigDecimal temp = main.getBigDecimal("temp");
                    if ( dateTime.getHour() == 12  ) {
                        forecastCollection.add(new SingleDayWeather(temp.doubleValue(), date));
                    }
                }

                EntityUtils.consume(entity);
                return forecastCollection;
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return forecast;
    }

    private String cityNameToNoWhiteSpaces(String cityName) {
        return cityName.replaceAll(" ", "%20");
    }
}
