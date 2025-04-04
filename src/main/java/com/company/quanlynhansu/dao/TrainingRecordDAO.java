package com.company.quanlynhansu.dao;

import com.company.quanlynhansu.model.TrainingRecord;
import com.company.quanlynhansu.util.DatabaseUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrainingRecordDAO {

    // Thêm 1 bản ghi đào tạo mới
    public boolean addTrainingRecord(TrainingRecord record) {
        String sql = "INSERT INTO training_record(employee_id, training_course, training_date, certificate, comments) " +
                     "VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, record.getEmployeeId());
            pstmt.setString(2, record.getTrainingCourse());
            pstmt.setDate(3, new java.sql.Date(record.getTrainingDate().getTime()));
            pstmt.setString(4, record.getCertificate());
            pstmt.setString(5, record.getComments());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Cập nhật bản ghi đào tạo
    public boolean updateTrainingRecord(TrainingRecord record) {
        String sql = "UPDATE training_record SET training_course = ?, training_date = ?, certificate = ?, comments = ? " +
                     "WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, record.getTrainingCourse());
            pstmt.setDate(2, new java.sql.Date(record.getTrainingDate().getTime()));
            pstmt.setString(3, record.getCertificate());
            pstmt.setString(4, record.getComments());
            pstmt.setInt(5, record.getId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Trong TrainingRecordDAO.java
    public List<TrainingRecord> getAllTrainingRecords() {
        List<TrainingRecord> records = new ArrayList<>();
        String sql = "SELECT * FROM training_record";
        try (Connection conn = DatabaseUtil.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()){
                TrainingRecord record = new TrainingRecord();
                record.setId(rs.getInt("id"));
                record.setEmployeeId(rs.getInt("employee_id"));
                record.setTrainingCourse(rs.getString("training_course"));
                record.setTrainingDate(rs.getDate("training_date"));
                record.setCertificate(rs.getString("certificate"));
                record.setComments(rs.getString("comments"));
                records.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }

    public List<TrainingRecord> getTrainingRecordsByEmployee(int employeeId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTrainingRecordsByEmployee'");
    }
}
