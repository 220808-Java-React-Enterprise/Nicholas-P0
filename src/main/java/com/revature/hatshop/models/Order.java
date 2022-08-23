package com.revature.hatshop.models;

public class Order {
    private String id;
    private String user_id;
    private String dt = "NONE";
    private String total = "0";

    private  String state = "CART";

    public Order(String id, String user_id, String dt, String total, String state) {
        this.id = id;
        this.user_id = user_id;
        this.dt = dt;
        this.total = total;
        this.state = state;
    }

    public Order(String id, String user_id) {
        this.id = id;
        this.user_id = user_id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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
