package com.revature.hatshop.ui;

import com.revature.hatshop.daos.UserDAO;
import com.revature.hatshop.models.User;
import com.revature.hatshop.services.UserService;

import java.util.Scanner;

public class AdminMenu implements IMenu {
    private final User user;
    private final UserService userService;

    public AdminMenu(User user, UserService userService) {
        this.user = user;
        this.userService = userService;
    }

    @Override
    public void start() {
        System.out.println("\nWelcome to the admin menu " + user.getUsername() + "!");
        Scanner scan = new Scanner(System.in);

        exit: {
            while (true) {
                System.out.println("\nMain menu " + user.getUsername() + "!");
                System.out.println("[1] Display Email");
                System.out.println("[2] Give another user admin privileges");
                System.out.println("[3] Take away user admin privileges");
                System.out.println("[4] Delete User [FOREVER]");
                System.out.println("[x] Sign out");
                System.out.print("\nEnter: ");

                switch (scan.nextLine()) {
                    case "1":
//                        System.out.println("Hello?");
                        System.out.println("\nEmail: " + user.getEmail()+ "\n");
                        break;
                    case "2":
                        addAdmin: {
                            while(true){
                                System.out.println("type in username to give admin role");
                                String uadd = scan.nextLine();
                                try{
                                    User newAdmin = new UserDAO().getUserByUsername(uadd);
                                }catch (Exception e){
                                    System.out.println("\nNot a valid user");
                                }

                                break addAdmin;
                            }
                        }
                        break;
                    case "3":
                        break;
                    case "4":
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
