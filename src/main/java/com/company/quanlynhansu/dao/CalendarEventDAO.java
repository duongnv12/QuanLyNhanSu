package com.company.quanlynhansu.dao;

import com.company.quanlynhansu.model.CalendarEvent;
import com.company.quanlynhansu.util.DatabaseUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CalendarEventDAO {

    public boolean addCalendarEvent(CalendarEvent event) {
        String sql = "INSERT INTO calendar_event(event_title, event_date, start_time, end_time, description) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, event.getEventTitle());
            pstmt.setDate(2, new java.sql.Date(event.getEventDate().getTime()));
            pstmt.setString(3, event.getStartTime());
            pstmt.setString(4, event.getEndTime());
            pstmt.setString(5, event.getDescription());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<CalendarEvent> getAllEvents() {
        List<CalendarEvent> events = new ArrayList<>();
        String sql = "SELECT id, event_title, event_date, start_time, end_time, description FROM calendar_event ORDER BY event_date, start_time";
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while(rs.next()){
                CalendarEvent event = new CalendarEvent();
                event.setId(rs.getInt("id"));
                event.setEventTitle(rs.getString("event_title"));
                event.setEventDate(rs.getDate("event_date"));
                event.setStartTime(rs.getString("start_time"));
                event.setEndTime(rs.getString("end_time"));
                event.setDescription(rs.getString("description"));
                events.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }
    
    // Nếu cần các phương thức update hoặc delete, có thể bổ sung thêm ở đây.
}
