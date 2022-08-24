package com.revature.hatshop.models;

import com.revature.hatshop.daos.OrderElementDAO;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private String id;
    private String userid;
    private String dt = "TIME";
    private String total = "0";
    private String state = "CART";
    private String location;

    private List<OrderElement> orderElements;

    public void addElement(OrderElement oe){

        this.orderElements.add(oe);
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Order(String id, String userid, String dt, String total, String state, String location) {
        this.id = id;
        this.userid = userid;
        this.dt = dt;
        this.total = total;
        this.state = state;
        this.location = location;

        if (new OrderElementDAO().getCart(userid) != null) {
            this.orderElements = new OrderElementDAO().getCart(userid);
        }else{
            this.orderElements = new ArrayList<>();
        }
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<OrderElement> getOrderElements() {
        return orderElements;
    }

    public void setOrderElements(List<OrderElement> orderElements) {
        this.orderElements = orderElements;
    }

}
