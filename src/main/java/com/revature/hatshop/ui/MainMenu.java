package com.revature.hatshop.ui;

import com.revature.hatshop.daos.ItemDAO;
import com.revature.hatshop.daos.OrderDAO;
import com.revature.hatshop.daos.UserDAO;
import com.revature.hatshop.models.Order;
import com.revature.hatshop.models.OrderElement;
import com.revature.hatshop.models.User;
import com.revature.hatshop.services.OrderService;
import com.revature.hatshop.services.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainMenu implements IMenu {
    private final User user;
    private final UserService userService;


    public MainMenu(User user, UserService userService) {
        this.user = user;
        this.userService = userService;
    }

    @Override
    public void start() {
        Scanner scan = new Scanner(System.in);

        exit: {
            while (true) {
                System.out.println("\nMain menu " + user.getUsername());
                System.out.println("[1] Display Email");
                System.out.println("[2] Shopping Menu");
                System.out.println("[3] View Previous Orders");
                System.out.println("[x] Sign out");
                System.out.print("\nEnter: ");

                switch (scan.nextLine()) {
                    case "1":
                        System.out.println("\nEmail: " + user.getEmail()+ "\n");
                        break;
                    case "2":
                        new OrderMenu(user, new OrderService(new OrderDAO()) ).start();
                        break;
                    case "3":
                        showOrders(user);
                        break;
                    case "x":
                        break exit;
                    default:
                        System.out.println("\nInvalid input!");
                        break;
                }
            }
        }
    }

    private void showOrders(User user) {
        List<Order> po= new ArrayList<>();
        po = OrderDAO.getPlaced(user);
        for (Order n:po){
            System.out.println("Order No. " + n.getId() + " for "+ user.getUsername());
            for (OrderElement m: n.getOrderElements()){
                System.out.println("\t\t"+m.getItemId()+ " --- " + new ItemDAO().getNameById(m.getItemId()) + " --- " + m.getQty() + " --- " + m.getPrice());
            }
        }
    }


}
