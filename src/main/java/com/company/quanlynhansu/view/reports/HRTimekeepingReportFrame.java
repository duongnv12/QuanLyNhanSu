package com.company.quanlynhansu.view.reports;

import com.company.quanlynhansu.dao.EmployeeDAO;
import com.company.quanlynhansu.dao.TimekeepingDAO;
import com.company.quanlynhansu.model.Employee;
import com.company.quanlynhansu.model.Timekeeping;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class HRTimekeepingReportFrame extends JFrame {

    private JTable reportTable;
    private ReportTableModel reportTableModel;

    public HRTimekeepingReportFrame() {
        setTitle("Báo cáo Chấm công - Ngày " + java.time.LocalDate.now());
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        // Lấy danh sách nhân viên từ EmployeeDAO
        EmployeeDAO employeeDAO = new EmployeeDAO();
        List<Employee> employees = employeeDAO.getAllEmployees();
        
        // Lấy thông tin chấm công của từng nhân viên cho ngày hiện tại
        TimekeepingDAO timekeepingDAO = new TimekeepingDAO();
        List<TimekeepingReportItem> items = new ArrayList<>();
        for (Employee emp : employees) {
            Timekeeping record = timekeepingDAO.getTodayRecord(emp.getId());
            items.add(new TimekeepingReportItem(emp, record));
        }
        
        reportTableModel = new ReportTableModel(items);
        reportTable = new JTable(reportTableModel);
        JScrollPane scrollPane = new JScrollPane(reportTable);
        add(scrollPane, BorderLayout.CENTER);
    }
    
    // Lớp đại diện mỗi hàng báo cáo, gồm thông tin nhân viên và bản ghi chấm công của ngày hôm nay
    public static class TimekeepingReportItem {
        private Employee employee;
        private Timekeeping record;
        
        public TimekeepingReportItem(Employee employee, Timekeeping record) {
            this.employee = employee;
            this.record = record;
        }
        
        public Employee getEmployee() {
            return employee;
        }
        
        public Timekeeping getRecord() {
            return record;
        }
    }
    
    // Table Model hiển thị các cột: ID, Tên nhân viên, Check In, Check Out, và Trạng thái.
    public static class ReportTableModel extends AbstractTableModel {
        private final String[] columns = {"Employee ID", "Employee Name", "Check In", "Check Out", "Status"};
        private final List<TimekeepingReportItem> items;
        
        public ReportTableModel(List<TimekeepingReportItem> items) {
            this.items = items;
        }
        
        @Override
        public int getRowCount() {
            return items.size();
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
            TimekeepingReportItem item = items.get(rowIndex);
            Employee emp = item.getEmployee();
            Timekeeping tk = item.getRecord();
            
            switch (columnIndex) {
                case 0: return emp.getId();
                case 1: return emp.getName();
                case 2:
                    return (tk != null && tk.getCheckIn() != null) ? tk.getCheckIn().toString() : "Chưa Check In";
                case 3:
                    return (tk != null && tk.getCheckOut() != null) ? tk.getCheckOut().toString() : "Chưa Check Out";
                case 4:
                    if (tk == null) {
                        return "Chưa điểm danh";
                    } else {
                        String status = "";
                        if (tk.getCheckIn() == null) status += "Chưa Check In ";
                        if (tk.getCheckOut() == null) status += "Chưa Check Out";
                        return status.isEmpty() ? "Hoàn tất" : status;
                    }
                default: return "";
            }
        }
    }
    
    // Main để test giao diện báo cáo chấm công
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HRTimekeepingReportFrame().setVisible(true));
    }
}
