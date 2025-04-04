package com.company.quanlynhansu.view.common;

import javax.swing.table.AbstractTableModel;
import com.company.quanlynhansu.model.Employee;
import com.company.quanlynhansu.controller.EmployeeController;
import java.util.List;

public class EmployeeTableModel extends AbstractTableModel {
    private String[] columns = {"ID", "Tên", "Chức vụ", "Lương"};
    private List<Employee> employees;

    public EmployeeTableModel() {
        refreshData();
    }

    public void refreshData() {
        employees = EmployeeController.getInstance().getEmployees();
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return employees != null ? employees.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Employee emp = employees.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return emp.getId();
            case 1:
                return emp.getName();
            case 2:
                return emp.getPosition();
            case 3:
                return emp.getSalary();
            default:
                return null;
        }
    }

    // Phương thức lấy đối tượng Employee theo chỉ số hàng
    public Employee getEmployeeAt(int rowIndex) {
        if (employees != null && rowIndex >= 0 && rowIndex < employees.size()) {
            return employees.get(rowIndex);
        }
        return null;
    }

    // Bổ sung thêm một method trong EmployeeTableModel.java (package: com.company.quanlynhansu.view.common)
    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
        fireTableDataChanged();
    }

}
