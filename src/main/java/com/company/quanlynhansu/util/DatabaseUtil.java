package com.company.quanlynhansu.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    private static Connection connection;

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                // Lấy thông tin kết nối từ biến môi trường
                String url = System.getenv("DB_URL");
                String username = System.getenv("DB_USERNAME");
                String password = System.getenv("DB_PASSWORD");

                // Nếu chưa có biến môi trường, sử dụng giá trị mặc định
                if (url == null) {
                    url = "jdbc:postgresql://localhost:5432/quanlynhansu";
                }
                if (username == null) {
                    username = "postgres";
                }
                if (password == null) {
                    password = "123456";
                }
                
                // In log để kiểm tra (chỉ dùng trong môi trường phát triển)
                System.out.println("Connecting to DB with URL: " + url);
                System.out.println("Using username: " + username + " and password: " + password);  
                
                connection = DriverManager.getConnection(url, username, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
