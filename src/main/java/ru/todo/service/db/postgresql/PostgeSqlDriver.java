package ru.todo.service.db.postgresql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PostgeSqlDriver {
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            Properties props = new Properties();
            try {
                props.load(PostgeSqlDriver.class.getClassLoader().getResourceAsStream("properties"));
            } catch (Exception e) {
                throw new SQLException("Failed to load database properties", e);
            }

            String url = props.getProperty("db.url");
            String username = props.getProperty("db.username");
            String password = props.getProperty("db.password");

            connection = DriverManager.getConnection(url, username, password);
        }
        return connection;
    }
}