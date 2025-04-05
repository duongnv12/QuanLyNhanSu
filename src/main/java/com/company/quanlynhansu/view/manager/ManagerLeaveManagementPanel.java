package com.company.quanlynhansu.view.manager;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import com.company.quanlynhansu.model.LeaveRequest;

public class ManagerLeaveManagementPanel extends JPanel {
    
    private JTable leaveTable;
    private LeaveTableModel tableModel;
    private JButton approveButton, rejectButton, refreshButton;
    
    public ManagerLeaveManagementPanel() {
        setLayout(new BorderLayout());
        tableModel = new LeaveTableModel();
        leaveTable = new JTable(tableModel);
        add(new JScrollPane(leaveTable), BorderLayout.CENTER);
        
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        approveButton = new JButton("Approve");
        rejectButton = new JButton("Reject");
        refreshButton = new JButton("Refresh");
        btnPanel.add(approveButton);
        btnPanel.add(rejectButton);
        btnPanel.add(refreshButton);
        add(btnPanel, BorderLayout.NORTH);
        
        approveButton.addActionListener(e -> onApprove());
        rejectButton.addActionListener(e -> onReject());
        refreshButton.addActionListener(e -> loadLeaveRequests());
        
        // Load danh sách yêu cầu nghỉ mẫu cho Manager
        loadLeaveRequests();
    }
    
    private void loadLeaveRequests() {
        // Thay thế bằng việc gọi Controller lấy dữ liệu từ DB theo nhóm Manager
        List<LeaveRequest> requests = new ArrayList<>();
        requests.add(new LeaveRequest(1, 1, java.sql.Date.valueOf("2025-04-10"), java.sql.Date.valueOf("2025-04-12"), "Nghỉ phép", "Sự kiện gia đình", "pending"));
        requests.add(new LeaveRequest(2, 2, java.sql.Date.valueOf("2025-04-15"), java.sql.Date.valueOf("2025-04-16"), "Nghỉ ốm", "Sốt cao", "pending"));
        tableModel.setLeaveRequests(requests);
    }
    
    private void onApprove() {
        int row = leaveTable.getSelectedRow();
        if(row < 0) {
            JOptionPane.showMessageDialog(this, "Chọn yêu cầu cần duyệt!");
            return;
        }
        LeaveRequest req = tableModel.getLeaveRequestAt(row);
        // Ở đây gọi controller update trạng thái đơn nghỉ (ví dụ update status thành "approved")
        req.setStatus("approved");
        JOptionPane.showMessageDialog(this, "Đã duyệt yêu cầu nghỉ của Employee ID: " + req.getEmployeeId());
        loadLeaveRequests();
    }
    
    private void onReject() {
        int row = leaveTable.getSelectedRow();
        if(row < 0) {
            JOptionPane.showMessageDialog(this, "Chọn yêu cầu cần từ chối!");
            return;
        }
        LeaveRequest req = tableModel.getLeaveRequestAt(row);
        // Cập nhật trạng thái đơn nghỉ thành "rejected"
        req.setStatus("rejected");
        JOptionPane.showMessageDialog(this, "Đã từ chối yêu cầu nghỉ của Employee ID: " + req.getEmployeeId());
        loadLeaveRequests();
    }
    
    private class LeaveTableModel extends AbstractTableModel {
        private final String[] columns = {"ID", "Employee ID", "Start Date", "End Date", "Type", "Reason", "Status"};
        private List<LeaveRequest> leaveRequests = new ArrayList<>();
        
        public void setLeaveRequests(List<LeaveRequest> requests) {
            this.leaveRequests = requests;
            fireTableDataChanged();
        }
        
        public LeaveRequest getLeaveRequestAt(int row) {
            return leaveRequests.get(row);
        }
        
        @Override
        public int getRowCount() {
            return leaveRequests != null ? leaveRequests.size() : 0;
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
                case 0: return req.getId();
                case 1: return req.getEmployeeId();
                case 2: return req.getStartDate();
                case 3: return req.getEndDate();
                case 4: return req.getLeaveType();
                case 5: return req.getReason();
                case 6: return req.getStatus();
                default: return "";
            }
        }
    }
}
