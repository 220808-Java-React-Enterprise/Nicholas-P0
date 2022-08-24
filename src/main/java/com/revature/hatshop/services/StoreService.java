package com.revature.hatshop.services;

import com.revature.hatshop.daos.StoreDAO;
import com.revature.hatshop.models.Store;

import java.util.List;

public class StoreService {
    public void listStores() {
        List<Store> stores = new StoreDAO().getAll();
        for (
                Store n : stores) {
            System.out.println("[" + n.getId() + "]....................." + n.getLocation());

        }
    }
}
