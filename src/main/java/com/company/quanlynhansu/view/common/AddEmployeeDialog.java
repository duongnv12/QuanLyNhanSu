package com.company.quanlynhansu.view.common;

import javax.swing.*;
import java.awt.*;
import com.company.quanlynhansu.controller.EmployeeController;
import com.company.quanlynhansu.model.Employee;

public class AddEmployeeDialog extends JDialog {
    private JTextField idField;
    private JTextField nameField;
    private JTextField positionField;
    private JTextField salaryField;
    private JButton addButton;
    private JButton cancelButton;

    public AddEmployeeDialog(JFrame parent) {
        super(parent, "Thêm Nhân Viên", true);
        initComponents();
        pack();
        setLocationRelativeTo(parent);
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        JPanel fieldsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // ID
        gbc.gridx = 0; gbc.gridy = 0;
        fieldsPanel.add(new JLabel("ID:"), gbc);
        gbc.gridx = 1;
        idField = new JTextField(15);
        fieldsPanel.add(idField, gbc);

        // Tên
        gbc.gridx = 0; gbc.gridy = 1;
        fieldsPanel.add(new JLabel("Tên:"), gbc);
        gbc.gridx = 1;
        nameField = new JTextField(15);
        fieldsPanel.add(nameField, gbc);

        // Chức vụ
        gbc.gridx = 0; gbc.gridy = 2;
        fieldsPanel.add(new JLabel("Chức vụ:"), gbc);
        gbc.gridx = 1;
        positionField = new JTextField(15);
        fieldsPanel.add(positionField, gbc);

        // Lương
        gbc.gridx = 0; gbc.gridy = 3;
        fieldsPanel.add(new JLabel("Lương:"), gbc);
        gbc.gridx = 1;
        salaryField = new JTextField(15);
        fieldsPanel.add(salaryField, gbc);

        add(fieldsPanel, BorderLayout.CENTER);

        // Nút điều khiển
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        addButton = new JButton("Thêm");
        cancelButton = new JButton("Hủy");
        btnPanel.add(addButton);
        btnPanel.add(cancelButton);
        add(btnPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> onAdd());
        cancelButton.addActionListener(e -> dispose());
    }

    private void onAdd() {
        try {
            int id = Integer.parseInt(idField.getText().trim());
            String name = nameField.getText().trim();
            String position = positionField.getText().trim();
            double salary = Double.parseDouble(salaryField.getText().trim());
            if (name.isEmpty() || position.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
                return;
            }
            Employee newEmp = new Employee(id, name, position, salary);
            boolean success = EmployeeController.getInstance().addEmployee(newEmp);
            if (success) {
                JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Có lỗi xảy ra, vui lòng kiểm tra lại!");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID và Lương phải là số hợp lệ!");
        }
    }
}
