package com.company.quanlynhansu.view.manager;

import javax.swing.*;
import java.awt.*;

public class ManagerDashboardFrame extends JFrame {

    private JTabbedPane tabbedPane;
    private TeamOverviewPanel teamOverviewPanel;
    private ManagerLeaveManagementPanel leaveManagementPanel;
    private ManagerNotificationPanel notificationPanel;

    public ManagerDashboardFrame() {
        setTitle("Manager Dashboard");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        tabbedPane = new JTabbedPane();

        // Tab 1: Hiển thị danh sách nhân viên thuộc nhóm (Team Overview)
        teamOverviewPanel = new TeamOverviewPanel();
        tabbedPane.addTab("Team Overview", teamOverviewPanel);

        // Tab 2: Quản lý đơn nghỉ phép (Leave Approval)
        leaveManagementPanel = new ManagerLeaveManagementPanel();
        tabbedPane.addTab("Leave Approval", leaveManagementPanel);

        // Tab 3: Thông báo & Lịch (Notification & Calendar)
        notificationPanel = new ManagerNotificationPanel();
        tabbedPane.addTab("Notification & Calendar", notificationPanel);

        add(tabbedPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ManagerDashboardFrame().setVisible(true));
    }
}
