package com.company.quanlynhansu.controller;

import com.company.quanlynhansu.dao.UserDAO;
import com.company.quanlynhansu.model.User;
import java.util.List;

public class UserController {
    private static UserController instance;
    private final UserDAO userDAO;

    private UserController() {
        userDAO = new UserDAO();
    }

    public static UserController getInstance() {
        if (instance == null) {
            instance = new UserController();
        }
        return instance;
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public boolean addUser(User user) {
        // Bạn có thể thêm các bước kiểm tra validate dữ liệu ở đây trước khi lưu
        return userDAO.addUser(user);
    }

    public boolean updateUser(User user) {
        return userDAO.updateUser(user);
    }

    public boolean deleteUser(int id) {
        return userDAO.deleteUser(id);
    }
}
