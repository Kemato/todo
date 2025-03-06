package ru.todo.service.db.postgresql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PostgreSqlDriver {
    private static Connection connection;
    private static final String PROPERTIES_FILE = "database.properties";

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            Properties props = loadProperties();
            String url = props.getProperty("db.url");
            String username = props.getProperty("db.username");
            String password = props.getProperty("db.password");

            if (url == null || username == null || password == null) {
                throw new SQLException("Missing required database properties (db.url, db.username, db.password)");
            }

            connection = DriverManager.getConnection(url, username, password);
        }
        return connection;
    }

    public static void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    private static Properties loadProperties() throws SQLException {
        Properties props = new Properties();
        try {
            props.load(PostgreSqlDriver.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE));
        } catch (Exception e) {
            throw new SQLException("Failed to load database properties from " + PROPERTIES_FILE, e);
        }
        return props;
    }
}