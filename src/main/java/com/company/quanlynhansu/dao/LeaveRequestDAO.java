package com.company.quanlynhansu.dao;

import com.company.quanlynhansu.model.LeaveRequest;
import com.company.quanlynhansu.util.DatabaseUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LeaveRequestDAO {

    public boolean addLeaveRequest(LeaveRequest request) {
        String sql = "INSERT INTO leave_request(employee_id, start_date, end_date, leave_type, reason, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, request.getEmployeeId());
            pstmt.setDate(2, new java.sql.Date(request.getStartDate().getTime()));
            pstmt.setDate(3, new java.sql.Date(request.getEndDate().getTime()));
            pstmt.setString(4, request.getLeaveType());
            pstmt.setString(5, request.getReason());
            pstmt.setString(6, request.getStatus());
            return pstmt.executeUpdate() > 0;
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateLeaveRequest(LeaveRequest request) {
        String sql = "UPDATE leave_request SET start_date = ?, end_date = ?, leave_type = ?, reason = ?, status = ? WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDate(1, new java.sql.Date(request.getStartDate().getTime()));
            pstmt.setDate(2, new java.sql.Date(request.getEndDate().getTime()));
            pstmt.setString(3, request.getLeaveType());
            pstmt.setString(4, request.getReason());
            pstmt.setString(5, request.getStatus());
            pstmt.setInt(6, request.getId());
            return pstmt.executeUpdate() > 0;
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateLeaveStatus(int requestId, String newStatus) {
        String sql = "UPDATE leave_request SET status = ? WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newStatus);
            pstmt.setInt(2, requestId);
            return pstmt.executeUpdate() > 0;
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Bạn có thể thêm các phương thức update (để duyệt yêu cầu), delete và getAllLeaveRequests(), getLeaveRequestByEmployee(id)...
    
    public List<LeaveRequest> getAllLeaveRequests() {
        List<LeaveRequest> requests = new ArrayList<>();
        String sql = "SELECT * FROM leave_request ORDER BY id ASC";
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while(rs.next()){
                LeaveRequest request = new LeaveRequest();
                request.setId(rs.getInt("id"));
                request.setEmployeeId(rs.getInt("employee_id"));
                request.setStartDate(rs.getDate("start_date"));
                request.setEndDate(rs.getDate("end_date"));
                request.setLeaveType(rs.getString("leave_type"));
                request.setReason(rs.getString("reason"));
                request.setStatus(rs.getString("status"));
                requests.add(request);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requests;
    }

    public boolean deleteLeaveRequest(int id) {
        String sql = "DELETE FROM leave_request WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public LeaveRequest getLeaveRequestById(int id) {
        String sql = "SELECT * FROM leave_request WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    LeaveRequest request = new LeaveRequest();
                    request.setId(rs.getInt("id"));
                    request.setEmployeeId(rs.getInt("employee_id"));
                    request.setStartDate(rs.getDate("start_date"));
                    request.setEndDate(rs.getDate("end_date"));
                    request.setLeaveType(rs.getString("leave_type"));
                    request.setReason(rs.getString("reason"));
                    request.setStatus(rs.getString("status"));
                    return request;
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<LeaveRequest> getLeaveRequestsByEmployeeId(int employeeId) {
        List<LeaveRequest> requests = new ArrayList<>();
        String sql = "SELECT * FROM leave_request WHERE employee_id = ? ORDER BY id ASC";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, employeeId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while(rs.next()){
                    LeaveRequest request = new LeaveRequest();
                    request.setId(rs.getInt("id"));
                    request.setEmployeeId(rs.getInt("employee_id"));
                    request.setStartDate(rs.getDate("start_date"));
                    request.setEndDate(rs.getDate("end_date"));
                    request.setLeaveType(rs.getString("leave_type"));
                    request.setReason(rs.getString("reason"));
                    request.setStatus(rs.getString("status"));
                    requests.add(request);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requests;
    }
}
