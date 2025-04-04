package com.company.quanlynhansu.controller;

import com.company.quanlynhansu.dao.PerformanceEvaluationDAO;
import com.company.quanlynhansu.model.PerformanceEvaluation;
import java.util.List;

public class PerformanceEvaluationController {
    private static PerformanceEvaluationController instance;
    private PerformanceEvaluationDAO evaluationDAO;

    private PerformanceEvaluationController() {
        evaluationDAO = new PerformanceEvaluationDAO();
    }

    public static PerformanceEvaluationController getInstance() {
        if (instance == null) {
            instance = new PerformanceEvaluationController();
        }
        return instance;
    }

    public boolean addEvaluation(PerformanceEvaluation evaluation) {
        // Có thể thêm logic nghiệp vụ kiểm tra dữ liệu trước khi lưu
        return evaluationDAO.addEvaluation(evaluation);
    }

    public boolean updateEvaluation(PerformanceEvaluation evaluation) {
        return evaluationDAO.updateEvaluation(evaluation);
    }

    public List<PerformanceEvaluation> getEvaluationsByEmployee(int employeeId) {
        return evaluationDAO.getEvaluationsByEmployee(employeeId);
    }
    
    public List<PerformanceEvaluation> getAllEvaluations() {
        return evaluationDAO.getAllEvaluations();
    }
}
