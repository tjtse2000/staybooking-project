package com.example.staybooking.repository;
import java.util.*;

public interface CustomLocationRepository {
    List<Long> searchByDistance(double lat, double lon, String dist);
}