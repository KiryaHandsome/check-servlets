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

    static {
        Properties properties = PropertiesLoader.loadProperties();
        JDBC_URL = properties.getProperty("url");
        USERNAME = properties.getProperty("username");
        PASSWORD = properties.getProperty("password");
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

}
