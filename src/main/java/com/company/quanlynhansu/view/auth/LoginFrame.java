package com.company.quanlynhansu.view.auth;

import javax.swing.*;
import java.awt.*;
import com.company.quanlynhansu.view.admin.AdminDashboardFrame;

public class LoginFrame extends JFrame {
    private JComboBox<String> roleComboBox;
    private JButton loginButton;

    public LoginFrame() {
        setTitle("Đăng Nhập Hệ Thống Quản Lý Nhân Sự");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JLabel roleLabel = new JLabel("Chọn vai trò:");
        String[] roles = {"Admin/HR Manager", "Quản lý", "Nhân viên"};
        roleComboBox = new JComboBox<>(roles);
        loginButton = new JButton("Đăng nhập");

        loginButton.addActionListener(e -> {
            String selectedRole = (String) roleComboBox.getSelectedItem();
            if (selectedRole.equals("Admin/HR Manager")) {
                new AdminDashboardFrame().setVisible(true);
            } else if (selectedRole.equals("Quản lý")) {
                JOptionPane.showMessageDialog(this, "Chức năng Quản lý chưa được triển khai.");
            } else if (selectedRole.equals("Nhân viên")) {
                JOptionPane.showMessageDialog(this, "Chức năng Nhân viên chưa được triển khai.");
            }
            dispose();
        });

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(roleLabel, gbc);
        gbc.gridx = 1;
        panel.add(roleComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(loginButton, gbc);
        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}
