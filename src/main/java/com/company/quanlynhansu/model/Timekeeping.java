package com.company.quanlynhansu.model;

import java.sql.Timestamp;
import java.util.Date;

public class Timekeeping {
    private int id;
    private int employeeId;
    private Timestamp checkIn;
    private Timestamp checkOut;
    private Date workDate;

    public Timekeeping() {}

    public Timekeeping(int id, int employeeId, Timestamp checkIn, Timestamp checkOut, Date workDate) {
        this.id = id;
        this.employeeId = employeeId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.workDate = workDate;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getEmployeeId() { return employeeId; }
    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }

    public Timestamp getCheckIn() { return checkIn; }
    public void setCheckIn(Timestamp checkIn) { this.checkIn = checkIn; }

    public Timestamp getCheckOut() { return checkOut; }
    public void setCheckOut(Timestamp checkOut) { this.checkOut = checkOut; }

    public Date getWorkDate() { return workDate; }
    public void setWorkDate(Date workDate) { this.workDate = workDate; }
}
