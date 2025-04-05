package com.company.quanlynhansu.view.employee;

import javax.swing.*;
import java.awt.*;

public class LeaveRequestPanel extends JPanel {
    private JTextField startDateField, endDateField;
    private JComboBox<String> leaveTypeCombo;
    private JTextArea reasonArea;
    private JButton submitButton;
    private JTextArea statusArea;

    public LeaveRequestPanel() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Gửi yêu cầu nghỉ phép"));

        formPanel.add(new JLabel("Ngày bắt đầu (yyyy-mm-dd):"));
        startDateField = new JTextField();
        formPanel.add(startDateField);

        formPanel.add(new JLabel("Ngày kết thúc (yyyy-mm-dd):"));
        endDateField = new JTextField();
        formPanel.add(endDateField);

        formPanel.add(new JLabel("Loại nghỉ:"));
        leaveTypeCombo = new JComboBox<>(new String[] {"Nghỉ phép", "Nghỉ ốm", "Nghỉ không lương"});
        formPanel.add(leaveTypeCombo);

        formPanel.add(new JLabel("Lý do:"));
        reasonArea = new JTextArea(3, 20);
        JScrollPane reasonScroll = new JScrollPane(reasonArea);
        formPanel.add(reasonScroll);

        submitButton = new JButton("Gửi yêu cầu");
        formPanel.add(submitButton);

        submitButton.addActionListener(e -> {
            // Trong thực tế, gọi controller để gửi yêu cầu nghỉ phép
            String requestInfo = "Yêu cầu nghỉ từ " + startDateField.getText() + " đến " +
                                 endDateField.getText() + ", loại: " + leaveTypeCombo.getSelectedItem() +
                                 ", lý do: " + reasonArea.getText() + "\n";
            statusArea.append(requestInfo);
            // Reset form sau khi gửi
            startDateField.setText("");
            endDateField.setText("");
            reasonArea.setText("");
        });

        add(formPanel, BorderLayout.NORTH);

        statusArea = new JTextArea(10, 40);
        statusArea.setEditable(false);
        statusArea.setBorder(BorderFactory.createTitledBorder("Trạng thái yêu cầu"));
        add(new JScrollPane(statusArea), BorderLayout.CENTER);
    }
}
