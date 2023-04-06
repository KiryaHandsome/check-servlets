package ru.clevertec.checkservlets.dao;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import ru.clevertec.checkservlets.util.PropertiesLoader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class DaoDataSourceTest {

    private static Logger logger = LogManager.getLogger(DaoDataSourceTest.class);

    @Test
    void propertiesTest() {
        Properties properties = PropertiesLoader.loadProperties();
        String JDBC_URL = properties.getProperty("url");
        String USERNAME = properties.getProperty("username");
        String PASSWORD = properties.getProperty("password");
        String DRIVER_CLASS_NAME = properties.getProperty("driver-class-name");
        try {
            Class.forName(DRIVER_CLASS_NAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String ddlPath = properties.getProperty("ddl");
        String dmlPath = properties.getProperty("dml");
        if(ddlPath != null || dmlPath != null) {
            try (Connection connection = DaoDataSource.getConnection();
                 Statement statement = connection.createStatement()){
                if(ddlPath != null) {
                    logger.debug(ddlPath);
                    InputStream inputStream = new FileInputStream(ddlPath);
                    logger.debug(Paths.get(ddlPath));
                    String ddlContent = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
                    statement.execute(ddlContent);
                    logger.debug("");
                }
                if(dmlPath != null) {
                    String dmlContent = new String(Files.readAllBytes(Paths.get(dmlPath)));
                    statement.execute(dmlContent);
                }
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
