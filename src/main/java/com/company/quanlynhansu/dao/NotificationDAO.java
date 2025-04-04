package com.company.quanlynhansu.dao;

import com.company.quanlynhansu.model.Notification;
import com.company.quanlynhansu.util.DatabaseUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotificationDAO {

    public boolean addNotification(Notification notification) {
        String sql = "INSERT INTO notifications(title, message, created_at) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, notification.getTitle());
            pstmt.setString(2, notification.getMessage());
            pstmt.setTimestamp(3, new Timestamp(notification.getCreatedAt().getTime()));
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Notification> getAllNotifications() {
        List<Notification> notifications = new ArrayList<>();
        String sql = "SELECT id, title, message, created_at FROM notifications ORDER BY created_at DESC";
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while(rs.next()){
                Notification notification = new Notification();
                notification.setId(rs.getInt("id"));
                notification.setTitle(rs.getString("title"));
                notification.setMessage(rs.getString("message"));
                notification.setCreatedAt(rs.getTimestamp("created_at"));
                notifications.add(notification);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notifications;
    }
    
    // Các phương thức update hoặc delete có thể được thêm nếu cần
}
