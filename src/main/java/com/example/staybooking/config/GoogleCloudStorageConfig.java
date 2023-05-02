package com.example.staybooking.config;

import com.google.auth.Credentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import org.springframework.context.annotation.*;
import com.google.cloud.storage.*;
import java.io.IOException;

@Configuration
public class GoogleCloudStorageConfig {
    @Bean
    public Storage storage() throws IOException {
        Credentials credentials = ServiceAccountCredentials.fromStream(getClass().getClassLoader().getResourceAsStream("credentials.json"));
        return StorageOptions.newBuilder().setCredentials(credentials).build().getService();
    }
}