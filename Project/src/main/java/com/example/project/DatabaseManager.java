package com.example.project;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class DatabaseManager {

    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_USER_URL = "jdbc:mysql://localhost:3306/users_schema"; // Update with your database URL
    private static final String DB_USER = "root"; // Update with your database username
    private static final String DB_PASSWORD = "admin"; // Update with your database password
    private static final Connection connection = startConnection();

    private static Connection startConnection(){
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

            // Establish a connection to the database
            return DriverManager.getConnection(JDBC_USER_URL, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static boolean addUserToDatabase(String username, String password){
        try {
            String sql = "INSERT INTO users (username, pswrd) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username); // Replace username with the actual username
            preparedStatement.setString(2, password); // Replace password with the actual password

            // Execute the insert statement
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("något gick kaputt");
            //throw new RuntimeException(e);
        }
        return false;
    }

    public static boolean logIn(String loginUsername, String password) {
        String sql = "SELECT pswrd FROM users WHERE username = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, loginUsername);
            System.out.println(preparedStatement);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String storedPassword = resultSet.getString("pswrd");
                    // Compare the provided password with the stored password
                    if(storedPassword.equals(password)) {
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false; // Return false if there's an error or no matching user found
    }

    public static ArrayList<String[]> getShoppingCart(String username){
        String sql = "SELECT uc.itemId, i.itemName, i.price, uc.quantity " +
                     "FROM userscart uc " +
                     "INNER JOIN items i ON uc.itemId = i.itemId " +
                     "WHERE uc.username = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            System.out.println(preparedStatement);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                ArrayList<String[]> cart = new ArrayList<>();
                while (resultSet.next()) {
                    String[] tempArray = new String[4];
                    tempArray[0] = resultSet.getString("itemId");
                    tempArray[1] = resultSet.getString("itemName");
                    tempArray[2] = resultSet.getString("price");
                    tempArray[3] = resultSet.getString("quantity");
                    System.out.println("temp array 3: " + tempArray[3]);
                    cart.add(tempArray);
                }
                return cart;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public static ArrayList<String[]> getProducts(){
        String sql = "SELECT * FROM items";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                ArrayList<String[]> items = new ArrayList<>();
                while (resultSet.next()) {
                    String[] tempArray = new String[3];
                    tempArray[0] = resultSet.getString("itemId");
                    tempArray[1] = resultSet.getString("itemName");
                    tempArray[2] = resultSet.getString("price");
                    items.add(tempArray);
                }

                return items;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null; // Return false if there's an error or no matching user found
    }

    public static boolean addToUserCart(String username, String password, int itemId){
        try {
            String sql = "INSERT INTO userscart (username, itemId, quantity)" +
                         "VALUES (?, ?, 1)" +
                         "ON DUPLICATE KEY UPDATE quantity = quantity + 1;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username); // Replace username with the actual username
            preparedStatement.setInt(2, itemId); // Replace password with the actual password

            // Execute the insert statement
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("något gick kaputt");
            //throw new RuntimeException(e);
        }
        return false;
    }

    public static boolean authenticateUser(String username, String password){
        String sql = "SELECT COUNT(*) FROM users WHERE username = ? AND pswrd = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                System.out.println("bra");
                if(resultSet.next()){
                    if(resultSet.getInt(1) == 1){
                        return true;
                    }
                }
                return false;
            }
        } catch (SQLException e) {
            System.out.println("fel fel fel: " + e);
        }
        return false; // Return false if there's an error or no matching user found
    }
}