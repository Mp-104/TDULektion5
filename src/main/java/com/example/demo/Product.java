package com.example.demo;

public class Product {

    int id;

    String name;

    double price;


    public Product (int id, String name, long price) {
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

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public double getPrice () {
        return price;
    }

    public void setPrice (long price) {
        this.price = price;
    }
}
