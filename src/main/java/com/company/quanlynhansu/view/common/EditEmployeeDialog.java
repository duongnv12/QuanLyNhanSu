package com.company.quanlynhansu.view.common;

import javax.swing.*;
import java.awt.*;
import com.company.quanlynhansu.controller.EmployeeController;
import com.company.quanlynhansu.model.Employee;

public class EditEmployeeDialog extends JDialog {
    private JTextField idField;
    private JTextField nameField;
    private JTextField positionField;
    private JTextField salaryField;
    private JButton updateButton;
    private JButton cancelButton;
    private Employee employee;

    public EditEmployeeDialog(JFrame parent, Employee employee) {
        super(parent, "Cập nhật thông tin nhân viên", true);
        this.employee = employee;
        initComponents();
        loadData();
        pack();
        setLocationRelativeTo(parent);
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        JPanel fieldsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Hiển thị ID (không cho sửa)
        gbc.gridx = 0; gbc.gridy = 0;
        fieldsPanel.add(new JLabel("ID:"), gbc);
        gbc.gridx = 1;
        idField = new JTextField(15);
        idField.setEditable(false);
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
        updateButton = new JButton("Cập nhật");
        cancelButton = new JButton("Hủy");
        btnPanel.add(updateButton);
        btnPanel.add(cancelButton);
        add(btnPanel, BorderLayout.SOUTH);

        updateButton.addActionListener(e -> onUpdate());
        cancelButton.addActionListener(e -> dispose());
    }

    private void loadData() {
        if (employee != null) {
            idField.setText(String.valueOf(employee.getId()));
            nameField.setText(employee.getName());
            positionField.setText(employee.getPosition());
            salaryField.setText(String.valueOf(employee.getSalary()));
        }
    }

    private void onUpdate() {
        try {
            String name = nameField.getText().trim();
            String position = positionField.getText().trim();
            double salary = Double.parseDouble(salaryField.getText().trim());
            if (name.isEmpty() || position.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
                return;
            }
            // Cập nhật lại đối tượng employee
            employee.setName(name);
            employee.setPosition(position);
            employee.setSalary(salary);
            boolean success = EmployeeController.getInstance().updateEmployee(employee);
            if (success) {
                JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại, vui lòng kiểm tra lại!");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Lương phải là số hợp lệ!");
        }
    }
}
