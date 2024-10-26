package org.example.model;

public class FailedToGetWeatherException extends RuntimeException {
    public FailedToGetWeatherException(String message, Throwable cause) {
        super(message, cause);
    }
}
