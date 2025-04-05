package com.company.quanlynhansu.view.employee;

import javax.swing.*;
import java.awt.*;

public class PersonalInfoPanel extends JPanel {
    private JTextField nameField, emailField, phoneField;
    private JButton updateButton;

    public PersonalInfoPanel() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Thông tin cá nhân"));

        formPanel.add(new JLabel("Họ và tên:"));
        nameField = new JTextField("Nguyen Van A");
        formPanel.add(nameField);

        formPanel.add(new JLabel("Email:"));
        emailField = new JTextField("nva@example.com");
        formPanel.add(emailField);

        formPanel.add(new JLabel("Số điện thoại:"));
        phoneField = new JTextField("0123456789");
        formPanel.add(phoneField);

        updateButton = new JButton("Cập nhật thông tin");
        formPanel.add(updateButton);

        updateButton.addActionListener(e -> {
            // Ở đây sẽ thực hiện validate và gọi controller để cập nhật thông tin
            JOptionPane.showMessageDialog(this, "Thông tin đã được cập nhật.");
        });

        add(formPanel, BorderLayout.NORTH);
    }
}
