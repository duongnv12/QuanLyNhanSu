package com.company.quanlynhansu.model;

import java.util.Date;

public class LeaveRequest {
    private int id;
    private int employeeId;
    private Date startDate;
    private Date endDate;
    private String leaveType;   // VD: nghỉ ốm, nghỉ phép, nghỉ việc,...
    private String reason;
    private String status;      // VD: pending, approved, rejected

    public LeaveRequest() {}

    public LeaveRequest(int id, int employeeId, Date startDate, Date endDate, String leaveType, String reason, String status) {
        this.id = id;
        this.employeeId = employeeId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.leaveType = leaveType;
        this.reason = reason;
        this.status = status;
    }

    // Getters & Setters...
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getEmployeeId() { return employeeId; }
    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }

    public Date getStartDate() { return startDate; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }

    public Date getEndDate() { return endDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }

    public String getLeaveType() { return leaveType; }
    public void setLeaveType(String leaveType) { this.leaveType = leaveType; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
