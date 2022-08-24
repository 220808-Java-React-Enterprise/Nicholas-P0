package com.revature.hatshop.ui;

import com.revature.hatshop.daos.ItemDAO;
import com.revature.hatshop.daos.StoreDAO;
import com.revature.hatshop.models.*;
import com.revature.hatshop.services.OrderService;

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
                System.out.println("[4] View available stock");
                System.out.println("[5] View Placed Orders");
                System.out.println("[x] Exit");
                switch (scan.nextLine()){
                    case "1":   //View current order
                        viewOrder();
                        break;
                    case "2":   //Add Items
                        addItems(scan);
                        break;
                    case "3":   //Remove Items
                        removeItems();
                        break;
                    case "4":   //View available Items
                        viewItems();
                        break;
                    case "5":   //View Placed Orders
                        viewPlaced();
                        break;
                    case "x":
                        break ordermenu;
                    default:
                        System.out.println("Not a Valid input.");
                        break;
                }
            }
        }
    }

    private void viewPlaced() {

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
                                System.out.println(n.getQty() + " is greater than " + qItem);
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
        System.out.println(order.getOrderElements());
    }

    private void viewOrder(){

        // show this.order with format...
        //  (item no)....(item name)....(quantity)....(price)
        //  (item no)....(item name)....(quantity)....(price)
        //  (item no)....(item name)....(quantity)....(price)
        //

    }
}
