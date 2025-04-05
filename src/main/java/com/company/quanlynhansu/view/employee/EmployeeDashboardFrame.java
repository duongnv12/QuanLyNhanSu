package com.company.quanlynhansu.view.employee;

import javax.swing.*;
import java.awt.*;

public class EmployeeDashboardFrame extends JFrame {
    private JTabbedPane tabbedPane;
    private EmployeeTimekeepingPanel timekeepingPanel;
    private LeaveRequestPanel leaveRequestPanel;
    private PersonalInfoPanel personalInfoPanel;
    private EmployeeNotificationPanel notificationPanel;

    public EmployeeDashboardFrame() {
        setTitle("Employee Dashboard");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        tabbedPane = new JTabbedPane();

        timekeepingPanel = new EmployeeTimekeepingPanel();
        tabbedPane.addTab("Chấm công", timekeepingPanel);

        leaveRequestPanel = new LeaveRequestPanel();
        tabbedPane.addTab("Nghỉ phép", leaveRequestPanel);

        personalInfoPanel = new PersonalInfoPanel();
        tabbedPane.addTab("Thông tin cá nhân", personalInfoPanel);

        notificationPanel = new EmployeeNotificationPanel();
        tabbedPane.addTab("Thông báo & Lịch", notificationPanel);

        add(tabbedPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EmployeeDashboardFrame().setVisible(true));
    }
}
