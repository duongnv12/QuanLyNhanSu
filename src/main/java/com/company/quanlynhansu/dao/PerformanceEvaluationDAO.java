package com.company.quanlynhansu.dao;

import com.company.quanlynhansu.model.PerformanceEvaluation;
import com.company.quanlynhansu.util.DatabaseUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PerformanceEvaluationDAO {

    // Thêm đánh giá mới
    public boolean addEvaluation(PerformanceEvaluation evaluation) {
        String sql = "INSERT INTO performance_evaluation(employee_id, evaluation_period, evaluator, rating, comments, evaluation_date) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, evaluation.getEmployeeId());
            pstmt.setString(2, evaluation.getEvaluationPeriod());
            pstmt.setString(3, evaluation.getEvaluator());
            pstmt.setDouble(4, evaluation.getRating());
            pstmt.setString(5, evaluation.getComments());
            pstmt.setDate(6, new java.sql.Date(evaluation.getEvaluationDate().getTime()));
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Cập nhật đánh giá
    public boolean updateEvaluation(PerformanceEvaluation evaluation) {
        String sql = "UPDATE performance_evaluation SET evaluation_period = ?, evaluator = ?, rating = ?, comments = ?, evaluation_date = ? " +
                     "WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, evaluation.getEvaluationPeriod());
            pstmt.setString(2, evaluation.getEvaluator());
            pstmt.setDouble(3, evaluation.getRating());
            pstmt.setString(4, evaluation.getComments());
            pstmt.setDate(5, new java.sql.Date(evaluation.getEvaluationDate().getTime()));
            pstmt.setInt(6, evaluation.getId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Trong PerformanceEvaluationDAO.java
    public List<PerformanceEvaluation> getAllEvaluations() {
        List<PerformanceEvaluation> evaluations = new ArrayList<>();
        String sql = "SELECT * FROM performance_evaluation";
        try (Connection conn = DatabaseUtil.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()){
                PerformanceEvaluation eval = new PerformanceEvaluation();
                eval.setId(rs.getInt("id"));
                eval.setEmployeeId(rs.getInt("employee_id"));
                eval.setEvaluationPeriod(rs.getString("evaluation_period"));
                eval.setEvaluator(rs.getString("evaluator"));
                eval.setRating(rs.getDouble("rating"));
                eval.setComments(rs.getString("comments"));
                eval.setEvaluationDate(rs.getDate("evaluation_date"));
                evaluations.add(eval);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return evaluations;
    }

    public List<PerformanceEvaluation> getEvaluationsByEmployee(int employeeId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEvaluationsByEmployee'");
    }
}
