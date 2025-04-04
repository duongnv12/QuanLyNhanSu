package com.company.quanlynhansu.view.manager;

import javax.swing.*;
import java.awt.*;

public class ManagerDashboardFrame extends JFrame {
    public ManagerDashboardFrame() {
        setTitle("Dashboard Quản Lý - Quản lý");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JLabel label = new JLabel("Giao diện dành cho Quản lý");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);
    }
}
