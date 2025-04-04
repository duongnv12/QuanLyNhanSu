package com.company.quanlynhansu.model;

import java.util.Date;

public class TrainingRecord {
    private int id;
    private int employeeId;
    private String trainingCourse;  // Tên khóa đào tạo
    private Date trainingDate;      // Ngày đào tạo
    private String certificate;     // Thông tin chứng chỉ (hoặc file path, nếu cần)
    private String comments;        // Nhận xét hay ghi chú về khóa đào tạo

    public TrainingRecord() {}

    public TrainingRecord(int id, int employeeId, String trainingCourse, Date trainingDate, 
                          String certificate, String comments) {
        this.id = id;
        this.employeeId = employeeId;
        this.trainingCourse = trainingCourse;
        this.trainingDate = trainingDate;
        this.certificate = certificate;
        this.comments = comments;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getEmployeeId() { return employeeId; }
    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }

    public String getTrainingCourse() { return trainingCourse; }
    public void setTrainingCourse(String trainingCourse) { this.trainingCourse = trainingCourse; }

    public Date getTrainingDate() { return trainingDate; }
    public void setTrainingDate(Date trainingDate) { this.trainingDate = trainingDate; }

    public String getCertificate() { return certificate; }
    public void setCertificate(String certificate) { this.certificate = certificate; }

    public String getComments() { return comments; }
    public void setComments(String comments) { this.comments = comments; }
}
