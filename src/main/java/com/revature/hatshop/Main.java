package com.revature.hatshop;

import com.revature.hatshop.daos.UserDAO;
import com.revature.hatshop.services.UserService;
import com.revature.hatshop.ui.LoginMenu;

public class Main {
    public static void main(String[] args) {
        /* dependency injection */
//        UserDAO userDAO = new UserDAO();
//        UserService userService = new UserService(userDAO);
//        new LoginMenu(userService).start();

        new LoginMenu(new UserService(new UserDAO())).start();
    }
}
