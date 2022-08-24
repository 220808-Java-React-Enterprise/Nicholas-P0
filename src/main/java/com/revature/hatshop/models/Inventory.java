package com.revature.hatshop.models;

public class Inventory {
    private String itemid;
    private String locationid;
    private String qty;

    public Inventory(String itemid, String locationid, String qty) {
        this.itemid = itemid;
        this.locationid = locationid;
        this.qty = qty;
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public String getLocationid() {
        return locationid;
    }

    public void setLocationid(String locationid) {
        this.locationid = locationid;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }
}
