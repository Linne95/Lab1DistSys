package com.example.project;

import java.util.ArrayList;
import java.util.Calendar;

public class DBItemManager {

    /*public static String getAnswerForName(String name) {
        String answer = null;

        try {
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
    }*/

    public static boolean addUser(String username, String password) {
        return DatabaseManager.addUserToDatabase(username, password);
    }

    public static boolean logIn(String username, String password){
        return DatabaseManager.logIn(username, password);
    }

    public static ArrayList<String[]> getProducts() {
        return DatabaseManager.getProducts();
    }

    public static boolean addItemToCart(String username, String password, int itemId){
        return DatabaseManager.addToUserCart(username, password, itemId);
    }

    public static ArrayList<String[]> getShoppingCart(String username){
        return DatabaseManager.getShoppingCart(username);
    }

    public static boolean authenticateUser(String username, String password){
        return DatabaseManager.authenticateUser(username, password);
    }
}