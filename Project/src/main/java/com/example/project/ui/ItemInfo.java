package com.example.project.ui;

import com.example.project.bo.Item;

public class ItemInfo {
    /*
    A class that represents the info about a product that will be displayed in the ui
     */
    private int id;
    private String name;
    private double price;

    public ItemInfo(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
