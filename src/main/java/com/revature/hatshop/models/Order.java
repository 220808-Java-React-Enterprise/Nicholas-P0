package com.revature.hatshop.models;

public class Order {
    private String id;
    private String user_id;
    private String dt = "0000";
    private String total = "0";

    private  String state = "CART";

    public Order(String id, String user_id) {
        this.id = id;
        this.user_id = user_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
