package com.revature.hatshop.models;

public class OrderElement {
    private String orderId;
    private String userId;

    private String itemId;
    private String qty;
    private String price;



    public OrderElement(String orderId, String userId, String itemId, String qty, String price) {
        this.orderId = orderId;
        this.userId = userId;
        this.itemId = itemId;
        this.qty = qty;
        this.price = price;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderElement{" +
                "orderId='" + orderId + '\'' +
                ", userId='" + userId + '\'' +
                ", itemId='" + itemId + '\'' +
                ", qty='" + qty + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
