package com.company.quanlynhansu.view.admin;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.List;

import com.company.quanlynhansu.controller.EmployeeController;
import com.company.quanlynhansu.model.Employee;
import com.company.quanlynhansu.view.common.EmployeeTableModel;
import com.company.quanlynhansu.view.common.AddEmployeeDialog;
import com.company.quanlynhansu.view.common.EditEmployeeDialog;
import com.company.quanlynhansu.util.CSVImporter;
import com.company.quanlynhansu.util.ExcelImporter;

public class AdminDashboardFrame extends JFrame {
    private JTable employeeTable;
    private EmployeeTableModel tableModel;
    private JButton addEmployeeButton, editEmployeeButton, deleteEmployeeButton;
    private JButton importExcelButton, importCSVButton;
    
    // Các thành phần cho tìm kiếm
    private JTextField searchField;
    private JButton searchButton;
    private JButton clearSearchButton;

    public AdminDashboardFrame() {
        setTitle("Dashboard Quản Lý Nhân Sự");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        tableModel = new EmployeeTableModel();
        employeeTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Panel cho các nút chức năng cơ bản và Import
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        addEmployeeButton = new JButton("Thêm nhân viên");
        editEmployeeButton = new JButton("Sửa");
        deleteEmployeeButton = new JButton("Xoá");
        importExcelButton = new JButton("Nhập dữ liệu Excel");
        importCSVButton = new JButton("Nhập dữ liệu CSV");

        btnPanel.add(addEmployeeButton);
        btnPanel.add(editEmployeeButton);
        btnPanel.add(deleteEmployeeButton);
        btnPanel.add(importExcelButton);
        btnPanel.add(importCSVButton);

        // Panel cho chức năng tìm kiếm
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        searchField = new JTextField(20);
        searchButton = new JButton("Tìm kiếm");
        clearSearchButton = new JButton("Clear");
        searchPanel.add(new JLabel("Tìm kiếm:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(clearSearchButton);

        // Panel kết hợp các nút chức năng và tìm kiếm
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(btnPanel, BorderLayout.WEST);
        topPanel.add(searchPanel, BorderLayout.EAST);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        add(mainPanel);

        // Xử lý sự kiện cho các nút chức năng
        addEmployeeButton.addActionListener(e -> openAddEmployeeDialog());
        editEmployeeButton.addActionListener(e -> onEditEmployee());
        deleteEmployeeButton.addActionListener(e -> onDeleteEmployee());
        importExcelButton.addActionListener(e -> onImportExcel());
        importCSVButton.addActionListener(e -> onImportCSV());
        
        // Sự kiện cho tìm kiếm
        searchButton.addActionListener(e -> onSearch());
        clearSearchButton.addActionListener(e -> onClearSearch());
    }

    private void openAddEmployeeDialog() {
        AddEmployeeDialog dialog = new AddEmployeeDialog(this);
        dialog.setVisible(true);
        tableModel.refreshData();
    }

    private void onEditEmployee() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên để cập nhật!");
            return;
        }
        Employee selectedEmployee = tableModel.getEmployeeAt(selectedRow);
        EditEmployeeDialog editDialog = new EditEmployeeDialog(this, selectedEmployee);
        editDialog.setVisible(true);
        tableModel.refreshData();
    }

    private void onDeleteEmployee() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên để xoá!");
            return;
        }
        Employee selectedEmployee = tableModel.getEmployeeAt(selectedRow);
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Bạn có chắc muốn xoá nhân viên: " + selectedEmployee.getName() + "?",
                "Xác nhận xoá",
                JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            boolean success = EmployeeController.getInstance().deleteEmployee(selectedEmployee.getId());
            if (success) {
                JOptionPane.showMessageDialog(this, "Xoá nhân viên thành công!");
                tableModel.refreshData();
            } else {
                JOptionPane.showMessageDialog(this, "Xoá nhân viên thất bại!");
            }
        }
    }

    private void onImportExcel() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn file Excel chứa danh sách nhân viên");
        int userSelection = fileChooser.showOpenDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File excelFile = fileChooser.getSelectedFile();
            List<Employee> employeesToImport = ExcelImporter.importEmployees(excelFile);
            int countImported = 0;
            for (Employee emp : employeesToImport) {
                boolean success = EmployeeController.getInstance().addEmployee(emp);
                if (success) {
                    countImported++;
                }
            }
            JOptionPane.showMessageDialog(this,
                    "Nhập dữ liệu Excel hoàn thành: " + countImported + "/" + employeesToImport.size() 
                    + " nhân viên đã được thêm.");
            tableModel.refreshData();
        }
    }

    private void onImportCSV() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn file CSV chứa danh sách nhân viên");
        int userSelection = fileChooser.showOpenDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File csvFile = fileChooser.getSelectedFile();
            List<Employee> employeesToImport = CSVImporter.importEmployees(csvFile);
            int countImported = 0;
            for (Employee emp : employeesToImport) {
                boolean success = EmployeeController.getInstance().addEmployee(emp);
                if (success) {
                    countImported++;
                }
            }
            JOptionPane.showMessageDialog(this,
                    "Nhập dữ liệu CSV hoàn thành: " + countImported + "/" + employeesToImport.size() 
                    + " nhân viên đã được thêm.");
            tableModel.refreshData();
        }
    }
    
    // Sự kiện tìm kiếm
    private void onSearch() {
        String keyword = searchField.getText().trim();
        if (keyword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập từ khóa tìm kiếm");
            return;
        }
        List<Employee> result = EmployeeController.getInstance().searchEmployees(keyword);
        if (result.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên nào!");
        }
        // Cập nhật bảng với kết quả tìm kiếm
        tableModel.setEmployees(result);
    }
    
    // Sự kiện xóa bộ lọc tìm kiếm, load lại toàn bộ dữ liệu
    private void onClearSearch() {
        searchField.setText("");
        tableModel.refreshData();
    }
}
