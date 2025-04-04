package com.company.quanlynhansu.controller;

import com.company.quanlynhansu.dao.LeaveRequestDAO;
import com.company.quanlynhansu.model.LeaveRequest;
import java.util.List;

public class LeaveRequestController {
    private static LeaveRequestController instance;
    private final LeaveRequestDAO leaveRequestDAO;

    private LeaveRequestController() {
        leaveRequestDAO = new LeaveRequestDAO();
    }

    public static LeaveRequestController getInstance() {
        if (instance == null) {
            instance = new LeaveRequestController();
        }
        return instance;
    }

    public boolean addLeaveRequest(LeaveRequest request) {
        return leaveRequestDAO.addLeaveRequest(request);
    }

    public List<LeaveRequest> getAllLeaveRequests() {
        return leaveRequestDAO.getAllLeaveRequests();
    }

    // Phương thức cập nhật trạng thái đơn nghỉ (ví dụ: "approved", "rejected")
    public boolean updateLeaveStatus(int requestId, String newStatus) {
        // Lớp DAO cần có phương thức updateLeaveStatus, dưới đây là ví dụ cách triển khai trong DAO:
        // public boolean updateLeaveStatus(int requestId, String status) { ... }
        return leaveRequestDAO.updateLeaveStatus(requestId, newStatus);
    }
}
