package com.company.quanlynhansu.view.manager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ManagerNotificationPanel extends JPanel {
    private JList<String> notificationList;
    private DefaultListModel<String> listModel;
    private JButton refreshButton;

    public ManagerNotificationPanel() {
        initComponents();
        loadNotifications();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        listModel = new DefaultListModel<>();
        notificationList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(notificationList);
        add(scrollPane, BorderLayout.CENTER);

        refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> loadNotifications());
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(refreshButton);
        add(topPanel, BorderLayout.NORTH);
    }

    private void loadNotifications() {
        // Dữ liệu mẫu cho các thông báo của Manager
        List<String> sampleNotifications = new ArrayList<>();
        sampleNotifications.add("Cuộc họp nhóm lúc 9:00 sáng ngày mai.");
        sampleNotifications.add("Báo cáo hiệu suất cần được cập nhật.");
        sampleNotifications.add("Chính sách nghỉ phép mới đã có hiệu lực.");
        
        listModel.clear();
        for (String notif : sampleNotifications) {
            listModel.addElement(notif);
        }
    }
}
