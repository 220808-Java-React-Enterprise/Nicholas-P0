package com.revature.hatshop.ui;

import com.revature.hatshop.daos.ItemDAO;
import com.revature.hatshop.daos.OrderDAO;
import com.revature.hatshop.models.User;
import com.revature.hatshop.services.OrderService;
import com.revature.hatshop.services.UserService;

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
                System.out.println("[2] Order Menu");
                System.out.println("[3] View Order");
                System.out.println("[4] View Items");
                System.out.println("[x] Sign out");
                System.out.print("\nEnter: ");

                switch (scan.nextLine()) {
                    case "1":
                        System.out.println("\nEmail: " + user.getEmail()+ "\n");
                        break;
                    case "2":
                        new OrderMenu(user, new OrderService(new OrderDAO()) ).start();
                        break;
                    case "4":
                        System.out.println(new ItemDAO().getAll());
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


}
