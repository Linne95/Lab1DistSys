package com.example.project.bo;

import com.example.project.db.DBCart;

import java.util.Collection;

public class Cart {
    private int itemId;
    private String itemName;
    private double price;
    private int quantity;

    protected Cart(int itemId, String itemName, double price, int quantity){
        this.itemId = itemId;
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

    static public Boolean addItemToCart(String username, String password, int id) {
        return DBCart.addToUserCart(username, password, id);
    }

    static public Collection getShoppingCart(String username) {
        return DBCart.getShoppingCart(username);
    }

    static public boolean removeFromUserCart(String username, int quantity, int itemId){
        return DBCart.removeFromUserCart(username, quantity, itemId);
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
