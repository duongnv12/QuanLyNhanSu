package com.company.quanlynhansu.view.manager;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import com.company.quanlynhansu.model.Employee;

public class TeamOverviewPanel extends JPanel {
    
    private JTable teamTable;
    private TeamTableModel tableModel;
    
    public TeamOverviewPanel() {
        setLayout(new BorderLayout());
        tableModel = new TeamTableModel();
        teamTable = new JTable(tableModel);
        add(new JScrollPane(teamTable), BorderLayout.CENTER);
        // Load dữ liệu mẫu cho các nhân viên trong nhóm
        loadTeamEmployees();
    }
    
    private void loadTeamEmployees() {
        // Thay thế bằng gọi controller lấy dữ liệu theo Manager đang đăng nhập
        List<Employee> teamEmployees = new ArrayList<>();
        // Dữ liệu mẫu:
        teamEmployees.add(new Employee(1, "Nguyễn Văn A", "Developer", 5000.0));
        teamEmployees.add(new Employee(2, "Trần Thị B", "Tester", 4000.0));
        teamEmployees.add(new Employee(3, "Lê Văn C", "Designer", 4500.0));
        
        tableModel.setEmployees(teamEmployees);
    }
    
    class TeamTableModel extends AbstractTableModel {
        private final String[] columns = {"ID", "Name", "Position", "Salary"};
        private List<Employee> employees = new ArrayList<>();
        
        public void setEmployees(List<Employee> employees) {
            this.employees = employees;
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
            switch(columnIndex){
                case 0: return emp.getId();
                case 1: return emp.getName();
                case 2: return emp.getPosition();
                case 3: return emp.getSalary();
                default: return "";
            }
        }
    }
}
