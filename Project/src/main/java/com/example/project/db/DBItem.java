package com.example.project.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;
public class DBItem extends com.example.project.bo.Item{
    /*
    A method that gets all products from the Database and returns them in form of a vector
     */
    public static Collection getProducts(){
        Vector returnedProducts = new Vector();
        try {
            Connection connection = DBManager.getConnection();

            String sql = "SELECT * FROM items";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("itemId");
                String name = resultSet.getString("itemName");
                double price = resultSet.getDouble("price");
                returnedProducts.addElement(new DBItem(id, name, price));
            }
            return returnedProducts;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    private DBItem(int id, String name, double price){
        super(id, name, price);
    }
}
