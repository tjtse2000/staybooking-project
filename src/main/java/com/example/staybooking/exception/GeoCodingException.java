package com.example.staybooking.exception;

public class GeoCodingException extends RuntimeException {
    public GeoCodingException(String message) {
        super(message);
    }
}