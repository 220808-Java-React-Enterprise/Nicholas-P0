package com.revature.hatshop.ui;

import com.revature.hatshop.daos.StoreDAO;
import com.revature.hatshop.models.Store;
import com.revature.hatshop.models.User;
import com.revature.hatshop.services.StoreService;

import java.util.List;
import java.util.Scanner;

public class InventoryMenu implements IMenu{
    private Store store;
    private User upoint;
    User uPointer;
    @Override
    public void start() {
        invmenu:{
            StoreService storeService = new StoreService();
            Scanner scan = new Scanner(System.in);
            while (true){
                System.out.println("please select a store to View/Edit");
                storeService.listStores();

            }
        }

    }
}
