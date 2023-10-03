package com.example.project;

import java.sql.*;

public class DatabaseQuery {

    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/test_schema"; // Update with your database URL
    private static final String DB_USER = "root"; // Update with your database username
    private static final String DB_PASSWORD = "admin"; // Update with your database password

    public static String getAnswerForName(String name) {
        String answer = null;

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

            // Establish a connection to the database
            Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);

            // SQL query to retrieve the answer based on the provided name
            String sql = "SELECT status FROM test WHERE successful = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);

            // Execute the query and retrieve the result
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                answer = resultSet.getString("status");
            }

            // Close resources
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return answer;
    }

    public static String test() {
        String nameToQuery = "was it?";
        String answer = getAnswerForName(nameToQuery);

        if (answer != null) {
            System.out.println("Answer for '" + nameToQuery + "': " + answer);
        } else {
            System.out.println("No answer found for '" + nameToQuery + "'.");
        }
        return "Hello";
    }
}