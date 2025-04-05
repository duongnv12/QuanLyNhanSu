package com.company.quanlynhansu.view.employee;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeNotificationPanel extends JPanel {
    private JList<String> notificationList;
    private DefaultListModel<String> listModel;
    private JButton refreshButton;

    public EmployeeNotificationPanel() {
        initComponents();
        loadNotifications();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        notificationList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(notificationList);
        add(scrollPane, BorderLayout.CENTER);

        refreshButton = new JButton("Làm mới");
        refreshButton.addActionListener(e -> loadNotifications());
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(refreshButton);
        add(panel, BorderLayout.NORTH);
    }

    private void loadNotifications() {
        // Ở đây bạn có thể gọi NotificationController để lấy danh sách thông báo của nhân viên.
        // Ví dụ: giả lập dữ liệu mẫu.
        List<String> sampleNotifs = new ArrayList<>();
        sampleNotifs.add("Thông báo 1: Họp toàn công ty vào 10:00 sáng.");
        sampleNotifs.add("Thông báo 2: Update chính sách nghỉ phép mới.");
        sampleNotifs.add("Thông báo 3: Đăng ký đào tạo nội bộ ngày mai.");

        listModel.clear();
        for (String notif : sampleNotifs) {
            listModel.addElement(notif);
        }
    }
}
