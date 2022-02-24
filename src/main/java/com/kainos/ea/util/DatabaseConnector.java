package com.kainos.ea.util;

import com.kainos.ea.exception.DatabaseConnectionException;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DatabaseConnector {
    private static Connection conn;

    public static Connection getConnection() throws DatabaseConnectionException {
        String user;
        String password;
        String host;
        String database;

        if (conn != null) {
            return conn;
        }

        try {
            user            = System.getenv("DB_USERNAME");
            password        = System.getenv("DB_PASSWORD");
            host            = System.getenv("DB_HOST");
            database        = System.getenv("DB_NAME");

            if (user == null || password == null || host == null)
                throw new IllegalArgumentException(
                        "Properties file must exist and must contain "
                                + "user, password, and host properties.");

            conn = DriverManager.getConnection("jdbc:mysql://"
                    + host + "/" + database + "?allowPublicKeyRetrieval=true&useSSL=false", user, password);

            return conn;
        } catch (Exception e) {
            throw new DatabaseConnectionException(e);
        }
    }
}
