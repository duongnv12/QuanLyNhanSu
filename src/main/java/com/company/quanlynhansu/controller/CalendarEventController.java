package com.company.quanlynhansu.controller;

import com.company.quanlynhansu.dao.CalendarEventDAO;
import com.company.quanlynhansu.model.CalendarEvent;
import java.util.List;

public class CalendarEventController {
    private static CalendarEventController instance;
    private final CalendarEventDAO calendarEventDAO;

    private CalendarEventController() {
        calendarEventDAO = new CalendarEventDAO();
    }

    public static CalendarEventController getInstance() {
        if (instance == null) {
            instance = new CalendarEventController();
        }
        return instance;
    }

    public boolean addCalendarEvent(CalendarEvent event) {
        return calendarEventDAO.addCalendarEvent(event);
    }

    public List<CalendarEvent> getAllEvents() {
        return calendarEventDAO.getAllEvents();
    }
}
