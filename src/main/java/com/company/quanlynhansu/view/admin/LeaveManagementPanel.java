package com.company.quanlynhansu.view.admin;

import com.company.quanlynhansu.controller.LeaveRequestController;
import com.company.quanlynhansu.model.LeaveRequest;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.List;

public class LeaveManagementPanel extends JPanel {
    private JTable leaveTable;
    private LeaveTableModel tableModel;
    private JButton approveButton, rejectButton, refreshButton;
    
    public LeaveManagementPanel() {
        initComponents();
        loadLeaveRequests();
    }
    
    private void initComponents() {
        setLayout(new BorderLayout());
        tableModel = new LeaveTableModel();
        leaveTable = new JTable(tableModel);
        add(new JScrollPane(leaveTable), BorderLayout.CENTER);
        
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        approveButton = new JButton("Duyệt");
        rejectButton = new JButton("Từ chối");
        refreshButton = new JButton("Làm mới");
        
        btnPanel.add(approveButton);
        btnPanel.add(rejectButton);
        btnPanel.add(refreshButton);
        add(btnPanel, BorderLayout.NORTH);
        
        approveButton.addActionListener(e -> onApprove());
        rejectButton.addActionListener(e -> onReject());
        refreshButton.addActionListener(e -> loadLeaveRequests());
    }
    
    private void loadLeaveRequests() {
        List<LeaveRequest> requests = LeaveRequestController.getInstance().getAllLeaveRequests();
        tableModel.setLeaveRequests(requests);
    }
    
    private void onApprove() {
        int row = leaveTable.getSelectedRow();
        if(row < 0) {
            JOptionPane.showMessageDialog(this, "Chọn yêu cầu cần duyệt!");
            return;
        }
        LeaveRequest req = tableModel.getLeaveRequestAt(row);
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn duyệt yêu cầu nghỉ của Employee ID: " + req.getEmployeeId() + "?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if(confirm == JOptionPane.YES_OPTION) {
            boolean success = LeaveRequestController.getInstance().updateLeaveStatus(req.getId(), "approved");
            JOptionPane.showMessageDialog(this, success ? "Đã duyệt yêu cầu!" : "Có lỗi xảy ra khi duyệt yêu cầu!");
            loadLeaveRequests();
        }
    }
    
    private void onReject() {
        int row = leaveTable.getSelectedRow();
        if(row < 0) {
            JOptionPane.showMessageDialog(this, "Chọn yêu cầu cần từ chối!");
            return;
        }
        LeaveRequest req = tableModel.getLeaveRequestAt(row);
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn từ chối yêu cầu nghỉ của Employee ID: " + req.getEmployeeId() + "?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if(confirm == JOptionPane.YES_OPTION) {
            boolean success = LeaveRequestController.getInstance().updateLeaveStatus(req.getId(), "rejected");
            JOptionPane.showMessageDialog(this, success ? "Đã từ chối yêu cầu!" : "Có lỗi xảy ra khi từ chối yêu cầu!");
            loadLeaveRequests();
        }
    }
    
    private class LeaveTableModel extends AbstractTableModel {
        private final String[] columns = {"ID", "Employee ID", "Bắt đầu", "Kết thúc", "Loại nghỉ", "Lý do", "Trạng thái"};
        private List<LeaveRequest> leaveRequests;
        
        public void setLeaveRequests(List<LeaveRequest> requests) {
            this.leaveRequests = requests;
            fireTableDataChanged();
        }
        
        public LeaveRequest getLeaveRequestAt(int row) {
            return leaveRequests.get(row);
        }
        
        @Override
        public int getRowCount() {
            return leaveRequests == null ? 0 : leaveRequests.size();
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
            LeaveRequest req = leaveRequests.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return req.getId();
                case 1:
                    return req.getEmployeeId();
                case 2:
                    return req.getStartDate();
                case 3:
                    return req.getEndDate();
                case 4:
                    return req.getLeaveType();
                case 5:
                    return req.getReason();
                case 6:
                    return req.getStatus();
                default:
                    return "";
            }
        }
    }
}
