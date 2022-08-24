package com.revature.hatshop.models;

import com.revature.hatshop.daos.InventoryDOA;
import com.revature.hatshop.daos.ItemDAO;

import java.util.List;

public class Store {
    private String id;
    private String location;

    private List<Inventory> storeInventory;

    public Store(String id) {
        this.id = id;
        this.storeInventory = new InventoryDOA().getStoreInventory(id);
    }

    public List<Inventory> getStoreInventory() {
        return storeInventory;
    }

    public void setStoreInventory(List<Inventory> storeInventory) {
        this.storeInventory = storeInventory;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Store(String id, String location) {
        this.id = id;
        this.location = location;
    }
    public void printInv(){
        for(Inventory n:storeInventory){
            System.out.println(n.getItemid()+ "\t---\t" + new ItemDAO().getNameById(n.getItemid())+"\t---\t"+n.getQty());
//            System.out.println(n.getItemid()+ "\t---\t"+n.getQty());
        }
    }
}
