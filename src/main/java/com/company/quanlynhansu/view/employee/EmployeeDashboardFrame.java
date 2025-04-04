package com.company.quanlynhansu.view.employee;

import javax.swing.*;
import java.awt.*;

public class EmployeeDashboardFrame extends JFrame {
    public EmployeeDashboardFrame() {
        setTitle("Dashboard Quản Lý - Nhân viên");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JLabel label = new JLabel("Giao diện dành cho Nhân viên");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);
    }
}
