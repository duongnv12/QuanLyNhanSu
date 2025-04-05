package com.company.quanlynhansu.controller;

import com.company.quanlynhansu.dao.UserDAO;
import com.company.quanlynhansu.model.User;

public class LoginController {
    private static LoginController instance;
    private final UserDAO userDAO;

    private LoginController() {
        userDAO = new UserDAO();
    }

    public static LoginController getInstance() {
        if (instance == null) {
            instance = new LoginController();
        }
        return instance;
    }

    public User authenticate(String username, String password) {
        return userDAO.authenticate(username, password);
    }
}
