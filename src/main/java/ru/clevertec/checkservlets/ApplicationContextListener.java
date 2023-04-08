package ru.clevertec.checkservlets;


import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.apache.commons.io.IOUtils;
import ru.clevertec.checkservlets.dao.DaoDataSource;
import ru.clevertec.checkservlets.util.PropertiesLoader;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Properties;

@WebListener
public class ApplicationContextListener implements ServletContextListener {

    /**
     * On program startup executes ddl and/or dml scripts,
     * if their paths are presented in application.yml file.
     */
    @Override
    public void contextInitialized(ServletContextEvent event) {
        Properties properties = PropertiesLoader.loadProperties();
        String ddlPath = properties.getProperty("ddl");
        String dmlPath = properties.getProperty("dml");
        if (Objects.nonNull(ddlPath)) {
            executeSqlScript(ddlPath);
        }
        if (Objects.nonNull(dmlPath)) {
            executeSqlScript(dmlPath);
        }
    }

    /**
     * Executes sql script.
     *
     * @param pathToSql path to file with sql script in resources folder
     */
    private static void executeSqlScript(String pathToSql) {
        try (InputStream inputStream = ApplicationContextListener.class
                .getClassLoader()
                .getResourceAsStream(pathToSql)) {
            String content = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            DaoDataSource.executeSql(content);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}