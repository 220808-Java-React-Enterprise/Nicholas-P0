package com.revature.hatshop.services;

import com.revature.hatshop.daos.OrderDAO;
import com.revature.hatshop.daos.OrderElementDAO;
import com.revature.hatshop.daos.UserDAO;
import com.revature.hatshop.models.Order;
import com.revature.hatshop.models.OrderElement;
import com.revature.hatshop.models.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private final OrderDAO orderDAO;

    public OrderService(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    public static List<OrderElement> mergeElements(List<OrderElement> oe) {

        for (int i = 0; i< oe.size(); i++){
            for (int j = 0; j< oe.size(); j++){
                if (i != j && oe.get(i).getItemId().equals(oe.get(j).getItemId())){
                    //add qty from j to i
                    System.out.println("\nDuplicate order found.  Merging Elements");
                    oe.set(i,new OrderElement(oe.get(i).getOrderId(),
                            oe.get(i).getUserId(),
                            oe.get(i).getItemId(),
                            Integer.toString(Integer.valueOf(oe.get(i).getQty()) +  Integer.valueOf(oe.get(j).getQty())),
                            Float.toString(Float.valueOf(oe.get(i).getPrice()) +  Float.valueOf(oe.get(j).getPrice()))));
                    //remove j
                    oe.remove(j);
                }

            }
        }
        return oe;
    }

    public void createNewOrder(String usr) throws IOException {
        orderDAO.save(orderDAO.getById(usr));
    }

    public Order loadOrder(String usr){
        return orderDAO.getById(usr);
    }

    public void updateOrder(String usr){

    }

    public void uploadOrder(String usr){

    }

    public void addElement(String itemid, String qty){

    }
//    public void updateElements(String ){
//
//    }

    public String orderAsString(Order order){
        String order_s ="";
        return order_s;
    }

    public void placeOrder(String usr){
        orderDAO.placeOrder(orderDAO.getById(usr));
    }

//    update
}
