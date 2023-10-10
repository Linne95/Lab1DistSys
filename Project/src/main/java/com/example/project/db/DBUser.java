package com.example.project.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;
public class DBUser extends com.example.project.bo.User{
    /*
    A method that creates a new user in the database
    In params are the username of the new user and their desired password
    returns true if successful otherwise false
     */
    public static boolean addUserToDatabase(String username, String password){
        Vector userToReturn = new Vector();
        try {
            Connection connection = DBManager.getConnection();

            String sql = "INSERT INTO users (username, pswrd) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    /*
    A method that log in teh user and checks if their username and password is correct
    In params are the userename and password that will be checked
    returns true if successful otherwise false
     */
    public static boolean logIn(String loginUsername, String password) {
        Vector userToReturn = new Vector();
        try {
            Connection connection = DBManager.getConnection();

            String sql = "SELECT pswrd FROM users WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, loginUsername);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String storedPassword = resultSet.getString("pswrd");
                if(storedPassword.equals(password)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    /*
    A method that checks if the users credentials are still correct of if they mismatch
    In params are then current password and username
    returns true if they match up with the stored credentials otherwise false
     */
    public static boolean authenticateUser(String username, String password){
        try {
            Connection connection = DBManager.getConnection();
            String sql = "SELECT COUNT(*) FROM users WHERE username = ? AND pswrd = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()){
                    if(resultSet.getInt(1) == 1){
                        return true;
                    }
                }
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    private DBUser(String username, String password) {
        super(username, password);
    }
}
