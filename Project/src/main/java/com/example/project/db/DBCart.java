package com.example.project.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

public class DBCart extends com.example.project.bo.Cart{
    /*
    A method for getting a specific users shoppingcart from their username from the SQL database
    Returns a vector with the items in the cart
     */
    public static Collection getShoppingCart(String username){
        Vector fetchedCart = new Vector();
        try {
            Connection connection = DBManager.getConnection();

            String sql = "SELECT uc.itemId, i.itemName, i.price, uc.quantity " +
                    "FROM userscart uc " +
                    "INNER JOIN items i ON uc.itemId = i.itemId " +
                    "WHERE uc.username = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("itemId");
                String name = resultSet.getString("itemName");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                fetchedCart.addElement(new DBCart(id, name, price, quantity));
            }
            return fetchedCart;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    /*
    A method that adds an item to a users cart
    In params are username, the users password and the item id of the desired item
    returns true if successful otherwise false
     */
    public static boolean addToUserCart(String username, String password, int itemId){
        try {
            Connection connection = DBManager.getConnection();

            String sql = "INSERT INTO userscart (username, itemId, quantity)" +
                    "VALUES (?, ?, 1)" +
                    "ON DUPLICATE KEY UPDATE quantity = quantity + 1;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setInt(2, itemId);

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
    A method that removes an item from a users cart.
    in params are username of the user, what quantity the item is at currently and teh id of that item
    returns true if successful and false otherwise
     */
    public static boolean removeFromUserCart(String username, int quantity, int itemId){
        try {
            Connection connection = DBManager.getConnection();
            String sql = "";
            if(quantity==1){
                sql = "DELETE FROM usersCart " +
                        "WHERE userName = ? AND itemId = ? AND quantity = 1;";
            }else{
                sql = "UPDATE usersCart " +
                        "SET quantity = quantity - 1 " +
                        "WHERE userName = ? AND itemId = ? AND quantity > 1;";
            }

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setInt(2, itemId);

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

    private DBCart(int itemId, String itemName, double price, int quantity) {
        super(itemId, itemName, price, quantity);
    }
}
