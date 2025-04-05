package com.company.quanlynhansu.view.employee;

import javax.swing.*;
import java.awt.*;

public class EmployeeTimekeepingPanel extends JPanel {
    private JButton checkInButton;
    private JButton checkOutButton;
    private JTextArea historyArea; // Hiển thị lịch sử check-in/out

    public EmployeeTimekeepingPanel() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        checkInButton = new JButton("Check In");
        checkOutButton = new JButton("Check Out");
        buttonPanel.add(checkInButton);
        buttonPanel.add(checkOutButton);
        add(buttonPanel, BorderLayout.NORTH);

        historyArea = new JTextArea(10, 40);
        historyArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(historyArea);
        add(scrollPane, BorderLayout.CENTER);

        // Khi nhân viên bấm check in/out, lưu lại thời gian (giả lập)
        checkInButton.addActionListener(e -> {
            String timeStamp = java.time.LocalDateTime.now().toString();
            historyArea.append("Check In: " + timeStamp + "\n");
        });
        checkOutButton.addActionListener(e -> {
            String timeStamp = java.time.LocalDateTime.now().toString();
            historyArea.append("Check Out: " + timeStamp + "\n");
        });
    }
}
