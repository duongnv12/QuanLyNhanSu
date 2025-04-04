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

public class EmployeeManagementPanel extends JPanel {
    private JTable employeeTable;
    private EmployeeTableModel tableModel;
    private JButton addEmployeeButton, editEmployeeButton, deleteEmployeeButton;
    private JButton importExcelButton, importCSVButton, deleteAllButton;
    private JTextField searchField;
    private JButton searchButton, clearSearchButton;

    public EmployeeManagementPanel() {
        initComponents();
    }
    
    private void initComponents() {
        setLayout(new BorderLayout());
        
        // Khởi tạo table model và JTable để hiển thị danh sách nhân viên
        tableModel = new EmployeeTableModel();
        employeeTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        
        // Panel chứa các nút chức năng (CRUD, Import, Reset dữ liệu)
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        addEmployeeButton = new JButton("Thêm nhân viên");
        editEmployeeButton = new JButton("Sửa");
        deleteEmployeeButton = new JButton("Xoá");
        importExcelButton = new JButton("Nhập dữ liệu Excel");
        importCSVButton = new JButton("Nhập dữ liệu CSV");
        deleteAllButton = new JButton("Xóa toàn bộ");
        
        btnPanel.add(addEmployeeButton);
        btnPanel.add(editEmployeeButton);
        btnPanel.add(deleteEmployeeButton);
        btnPanel.add(importExcelButton);
        btnPanel.add(importCSVButton);
        btnPanel.add(deleteAllButton);
        
        // Panel chứa chức năng tìm kiếm
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        searchField = new JTextField(20);
        searchButton = new JButton("Tìm kiếm");
        clearSearchButton = new JButton("Clear");
        
        searchPanel.add(new JLabel("Tìm kiếm:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(clearSearchButton);
        
        // Top panel kết hợp các nút chức năng và panel tìm kiếm
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(btnPanel, BorderLayout.WEST);
        topPanel.add(searchPanel, BorderLayout.EAST);
        
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        
        // Đăng ký các sự kiện cho nút chức năng
        addEmployeeButton.addActionListener(e -> openAddEmployeeDialog());
        editEmployeeButton.addActionListener(e -> onEditEmployee());
        deleteEmployeeButton.addActionListener(e -> onDeleteEmployee());
        importExcelButton.addActionListener(e -> onImportExcel());
        importCSVButton.addActionListener(e -> onImportCSV());
        searchButton.addActionListener(e -> onSearch());
        clearSearchButton.addActionListener(e -> onClearSearch());
        deleteAllButton.addActionListener(e -> onDeleteAllEmployees());
    }

    // Mở dialog thêm nhân viên, sau đó làm mới bảng dữ liệu
    private void openAddEmployeeDialog() {
        AddEmployeeDialog dialog = new AddEmployeeDialog((JFrame) SwingUtilities.getWindowAncestor(this));
        dialog.setVisible(true);
        tableModel.refreshData();
    }
    
    // Nếu có nhân viên được chọn, mở dialog chỉnh sửa, sau đó làm mới bảng dữ liệu
    private void onEditEmployee() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên để cập nhật!");
            return;
        }
        Employee selectedEmployee = tableModel.getEmployeeAt(selectedRow);
        EditEmployeeDialog editDialog = new EditEmployeeDialog((JFrame) SwingUtilities.getWindowAncestor(this), selectedEmployee);
        editDialog.setVisible(true);
        tableModel.refreshData();
    }
    
    // Xoá nhân viên theo dòng được chọn, với xác nhận
    private void onDeleteEmployee() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên để xóa!");
            return;
        }
        Employee selectedEmployee = tableModel.getEmployeeAt(selectedRow);
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Bạn có chắc muốn xóa nhân viên: " + selectedEmployee.getName() + "?",
                "Xác nhận xóa",
                JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            boolean success = EmployeeController.getInstance().deleteEmployee(selectedEmployee.getId());
            if (success) {
                JOptionPane.showMessageDialog(this, "Xóa nhân viên thành công!");
                tableModel.refreshData();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa nhân viên thất bại!");
            }
        }
    }
    
    // Nhập dữ liệu từ file Excel cho danh sách nhân viên
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
                    "Nhập dữ liệu Excel hoàn thành: " + countImported + "/" + employeesToImport.size() +
                    " nhân viên được thêm.");
            tableModel.refreshData();
        }
    }
    
    // Nhập dữ liệu từ file CSV cho danh sách nhân viên
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
                    "Nhập dữ liệu CSV hoàn thành: " + countImported + "/" + employeesToImport.size() +
                    " nhân viên được thêm.");
            tableModel.refreshData();
        }
    }
    
    // Tìm kiếm nhân viên theo từ khóa nhập vào, cập nhật bảng hiển thị
    private void onSearch() {
        String keyword = searchField.getText().trim();
        if (keyword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập từ khóa tìm kiếm!");
            return;
        }
        List<Employee> result = EmployeeController.getInstance().searchEmployees(keyword);
        if (result.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên nào!");
        }
        tableModel.setEmployees(result);
    }
    
    // Xoá bộ lọc tìm kiếm, làm mới bảng hiển thị toàn bộ nhân viên
    private void onClearSearch() {
        searchField.setText("");
        tableModel.refreshData();
    }
    
    // Xoá toàn bộ dữ liệu nhân viên với xác nhận của người dùng
    private void onDeleteAllEmployees() {
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Bạn có chắc chắn muốn xóa TẤT CẢ dữ liệu nhân viên?",
                "Xác nhận xóa",
                JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            boolean success = EmployeeController.getInstance().deleteAllEmployees();
            if (success) {
                JOptionPane.showMessageDialog(this, "Xóa dữ liệu nhân viên thành công!");
                tableModel.refreshData();
            } else {
                JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi xóa dữ liệu.");
            }
        }
    }
}
