package com.company.quanlynhansu.model;

import java.util.Date;

public class PerformanceEvaluation {
    private int id;
    private int employeeId;
    private String evaluationPeriod;  // Ví dụ: "Quý I 2025" hoặc "Tháng 03/2025"
    private String evaluator;         // Người đánh giá, ví dụ: tên của người quản lý
    private double rating;            // Điểm số (ví dụ từ 1 đến 10)
    private String comments;          // Nhận xét, góp ý
    private Date evaluationDate;      // Ngày thực hiện đánh giá

    public PerformanceEvaluation() {}

    public PerformanceEvaluation(int id, int employeeId, String evaluationPeriod, String evaluator,
                                 double rating, String comments, Date evaluationDate) {
        this.id = id;
        this.employeeId = employeeId;
        this.evaluationPeriod = evaluationPeriod;
        this.evaluator = evaluator;
        this.rating = rating;
        this.comments = comments;
        this.evaluationDate = evaluationDate;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getEmployeeId() { return employeeId; }
    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }

    public String getEvaluationPeriod() { return evaluationPeriod; }
    public void setEvaluationPeriod(String evaluationPeriod) { this.evaluationPeriod = evaluationPeriod; }

    public String getEvaluator() { return evaluator; }
    public void setEvaluator(String evaluator) { this.evaluator = evaluator; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    public String getComments() { return comments; }
    public void setComments(String comments) { this.comments = comments; }

    public Date getEvaluationDate() { return evaluationDate; }
    public void setEvaluationDate(Date evaluationDate) { this.evaluationDate = evaluationDate; }
}
