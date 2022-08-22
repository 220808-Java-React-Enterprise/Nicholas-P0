package com.revature.hatshop.models;


public class Item {
    private String id;
    private String name;
    private String price;

    public Item(String id, String name, String price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' + '\n'+
                '}';
    }
}
