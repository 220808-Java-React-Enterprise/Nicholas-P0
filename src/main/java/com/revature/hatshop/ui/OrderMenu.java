package com.revature.hatshop.ui;

import com.revature.hatshop.daos.*;
import com.revature.hatshop.models.*;
import com.revature.hatshop.services.OrderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderMenu implements IMenu{
    private User user;
    private Order order;

    private Store store;
    private OrderService orderService;

    public OrderMenu(User user, OrderService orderService) {
        this.user = user;
        this.orderService = orderService;
        this.order = orderService.loadOrder(user.getId());

        this.store = new Store(user.getLocation());

    }

    @Override
    public void start() {
        Scanner scan = new Scanner(System.in);
        ordermenu:  {
            while(true){
                //Options
                System.out.println("You store is store number: "+store.getId());
                System.out.println("[1] View current order");
                System.out.println("[2] Add items to Order");
                System.out.println("[3] Remove items from order");
                System.out.println("[4] Place Order");
                System.out.println("[5] View Inventory");
                System.out.println("[x] Exit");
                switch (scan.nextLine()){
                    case "1":   //View current order
                        viewOrder();
                        break;
                    case "2":   //Add Items
                        addItems(scan);
                        updateElement();
                        break;
                    case "3":   //Remove Items
                        removeItems();
                    case "4":   //Place Order
                        submitOrder();
                        break;
                    case "5":   //View available Items
                        viewItems();
                        break;
                    case "x":
                        updateElement();
                        break ordermenu;
                    default:
                        System.out.println("Not a Valid input.");
                        break;
                }
            }
        }
    }

    private void updateElement(){
        for(OrderElement n: order.getOrderElements()){
            if (new OrderElementDAO().exists(n)){//checking if entry exists
                // update row
                System.out.println("Updating Entry");
                new OrderElementDAO().update(n);
            }else{
                // insert row
                System.out.println("Creating New Entry");
                new OrderElementDAO().insert(n);
            }
        }

    }
    private void submitOrder() {
//        for ()
        new OrderDAO().placeOrder(order);
        order = new Order(String.valueOf(Integer.valueOf(order.getId())+1), user.getId(), "0000", "0.00", "CART", user.getLocation());
        new OrderDAO().save(order);
        order.setOrderElements(new ArrayList<>());
        store = new Store(user.getLocation());

    }

    private void viewItems() {
        store.printInv();
    }

    private void removeItems() {
        //  Show order
        //  Prompt: (item no) & (qty)
        //  if qty is greater than element
        //      delete element
        //  else
        //      update element with new data

    }

    private void addItems(Scanner scan) {
        additem:
        {
            while (true) {

                store.printInv();
                System.out.println("\n");
                String aItem = "";
                String qItem = "";
                idcheck:
                {
                    while (true) {
                        System.out.println("What item would you like to add to your cart?");
                        aItem = scan.nextLine();
                        for (Inventory n : store.getStoreInventory()) {
                            if (aItem.equals(n.getItemid())) {
                                break idcheck;
                            }
                        }
                        System.out.println("Not a valid item id.");
                    }

                }

                qtycheck:
                {
                    while (true) {
                        System.out.println("How many?");
                        qItem = scan.nextLine();
                        for (Inventory n : store.getStoreInventory()) {
                            if (n.getItemid().equals(aItem) && Integer.valueOf(n.getQty()) >= Integer.valueOf(qItem)) {
//                                System.out.println(n.getQty() + " is greater than " + qItem);
                                ;
                                break qtycheck;
                            }

                        }
                        System.out.println("Not a valid quantity");
                    }
                }
                // Add Element to Order
                order.addElement(new OrderElement(order.getId(), user.getId(), aItem, qItem, Float.toString(Float.valueOf(qItem)*Float.valueOf(new ItemDAO().getPrice(aItem)))));
                order.setOrderElements(OrderService.mergeElements(order.getOrderElements()));
                break additem;


            }
        }
//        System.out.println(order.getOrderElements());
    }

    private void viewOrder(){
        System.out.println("id"+ "     " + "item" + "     " + "count" + "     " + "subtotal");
        Double total = 0.0;
        for (OrderElement n: order.getOrderElements()){
            System.out.println(n.getItemId()+ " --- " + new ItemDAO().getNameById(n.getItemId()) + " --- " + n.getQty() + " --- " + n.getPrice());
            total += Double.valueOf(n.getPrice());
        }
        System.out.println();
        System.out.println("Total: $" + total);


    }
}
