package ru.clevertec.checkservlets.dao;

import ru.clevertec.checkservlets.util.PropertiesLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DaoDataSource {

    private static final String JDBC_URL;
    private static final String USERNAME;
    private static final String PASSWORD;
    private static final String DRIVER_CLASS_NAME;

    static {
        Properties properties = PropertiesLoader.loadProperties();
        JDBC_URL = properties.getProperty("url");
        USERNAME = properties.getProperty("username");
        PASSWORD = properties.getProperty("password");
        DRIVER_CLASS_NAME = properties.getProperty("driver-class-name");
        try {
            Class.forName(DRIVER_CLASS_NAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

}
