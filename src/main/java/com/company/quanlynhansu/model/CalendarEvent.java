package com.company.quanlynhansu.model;

import java.util.Date;

public class CalendarEvent {
    private int id;
    private String eventTitle;
    private Date eventDate;
    private String startTime; // Có thể là dạng string, ví dụ "09:00"
    private String endTime;
    private String description;

    public CalendarEvent() {}

    public CalendarEvent(int id, String eventTitle, Date eventDate, String startTime, String endTime, String description) {
        this.id = id;
        this.eventTitle = eventTitle;
        this.eventDate = eventDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
    }

    // Getters & Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getEventTitle() {
        return eventTitle;
    }
    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }
    public Date getEventDate() {
        return eventDate;
    }
    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }
    public String getStartTime() {
        return startTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    public String getEndTime() {
        return endTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
