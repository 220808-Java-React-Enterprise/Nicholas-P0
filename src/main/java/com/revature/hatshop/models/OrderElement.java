package com.revature.hatshop.models;

public class OrderElement {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice_n() {
        return price_n;
    }

    public void setPrice_n(String price_n) {
        this.price_n = price_n;
    }

    private String id;
    private String order_id;
    private String item_id;
    private String quantity = "0";
    private String price_n;

    public OrderElement(String order_id, String item_id, String quantity) {

        this.order_id = order_id;
        this.item_id = item_id;
        this.quantity = quantity;


    }

    public OrderElement(String id, String order_id, String item_id, String quantity) {
        this.id = id;
        this.order_id = order_id;
        this.item_id = item_id;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderElement{" +
//                "id='" + id + '\'' +
//                ", order_id='" + order_id + '\'' +
                ", item_id='" + item_id + '\'' +
                ", quantity='" + quantity + '\'' + "\n" +
                '}';
    }
}
