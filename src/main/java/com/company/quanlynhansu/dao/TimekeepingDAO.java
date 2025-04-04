package com.company.quanlynhansu.dao;

import com.company.quanlynhansu.model.Timekeeping;
import com.company.quanlynhansu.util.DatabaseUtil;
import java.sql.*;

public class TimekeepingDAO {

    // Thêm bản ghi điểm danh (Check In) cho nhân viên
    public boolean insertCheckIn(Timekeeping record) {
        String sql = "INSERT INTO timekeeping(employee_id, check_in, work_date) VALUES (?, ?, CURRENT_DATE)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, record.getEmployeeId());
            pstmt.setTimestamp(2, record.getCheckIn());
            return pstmt.executeUpdate() > 0;
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Cập nhật bản ghi với thời gian Check Out
    public boolean updateCheckOut(Timekeeping record) {
        String sql = "UPDATE timekeeping SET check_out = ? WHERE employee_id = ? AND work_date = CURRENT_DATE";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setTimestamp(1, record.getCheckOut());
            pstmt.setInt(2, record.getEmployeeId());
            return pstmt.executeUpdate() > 0;
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Lấy bản ghi điểm danh trong ngày hiện tại của nhân viên
    public Timekeeping getTodayRecord(int employeeId) {
        String sql = "SELECT * FROM timekeeping WHERE employee_id = ? AND work_date = CURRENT_DATE";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, employeeId);
            try(ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                    Timekeeping record = new Timekeeping();
                    record.setId(rs.getInt("id"));
                    record.setEmployeeId(rs.getInt("employee_id"));
                    record.setCheckIn(rs.getTimestamp("check_in"));
                    record.setCheckOut(rs.getTimestamp("check_out"));
                    record.setWorkDate(rs.getDate("work_date"));
                    return record;
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
