package ru.clevertec.checkservlets.dao;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.clevertec.checkservlets.ApplicationContextListener;
import ru.clevertec.checkservlets.util.PropertiesLoader;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DaoDataSource {

    private static final String JDBC_URL;
    private static final String USERNAME;
    private static final String PASSWORD;
    private static final String DRIVER_CLASS_NAME;

    /*
      Initializes connection properties based on application.yml.
     */
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

    /**
     * Creates new connection with database based on properties in application.yml.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

    public static void executeSql(String sql) {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
