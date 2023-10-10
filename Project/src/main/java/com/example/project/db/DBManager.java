package com.example.project.db;

import java.sql.Connection;
import java.sql.DriverManager;;

public class DBManager {
    private static final String JDBC_USER_URL = "jdbc:mysql://localhost:3306/users_schema";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "admin";
    private static DBManager instance = null;
    private Connection connection = null;

    private static DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    private DBManager() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(JDBC_USER_URL, DB_USER, DB_PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    A method that gets an instance of teh connection to the db and returns it
     */
    public static Connection getConnection() {
        return getInstance().connection;
    }
}
