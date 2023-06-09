package ru.clevertec.checkservlets.util;

import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@UtilityClass
public class PropertiesLoader {

    public static Properties loadProperties() {
        Properties configuration = new Properties();
        try (InputStream inputStream = PropertiesLoader.class
                .getClassLoader()
                .getResourceAsStream("application.yml")) {
            configuration.load(inputStream);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return configuration;
    }
}
