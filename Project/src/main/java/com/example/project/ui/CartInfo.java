package com.example.project.ui;

public class CartInfo {
    /*
    A class that represents the info about a cart item that will be displayed in the ui
     */

    private int nutId;
    private String itemName;
    private double price;
    private int quantity;

    public CartInfo(int nutId, String itemName, double price, int quantity) {
        this.nutId = nutId;
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

    public int getNutId() {
        return nutId;
    }

    public void setNutId(int nutId) {
        this.nutId = nutId;
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
