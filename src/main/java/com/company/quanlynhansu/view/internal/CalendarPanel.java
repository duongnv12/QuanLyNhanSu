package com.company.quanlynhansu.view.internal;

import com.company.quanlynhansu.dao.CalendarEventDAO;
import com.company.quanlynhansu.model.CalendarEvent;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.List;

public class CalendarPanel extends JPanel {
    private JTable eventTable;
    private EventTableModel tableModel;
    private JButton addEventButton;
    
    public CalendarPanel() {
        initComponents();
        loadEvents();
    }
    
    private void initComponents() {
        setLayout(new BorderLayout());
        
        tableModel = new EventTableModel();
        eventTable = new JTable(tableModel);
        add(new JScrollPane(eventTable), BorderLayout.CENTER);
        
        addEventButton = new JButton("Thêm sự kiện mới");
        addEventButton.addActionListener(e -> openNewEventDialog());
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(addEventButton);
        add(topPanel, BorderLayout.NORTH);
    }
    
    private void loadEvents() {
        CalendarEventDAO dao = new CalendarEventDAO();
        List<CalendarEvent> events = dao.getAllEvents();
        tableModel.setEvents(events);
    }
    
    private void openNewEventDialog() {
        JTextField titleField = new JTextField(20);
        JTextField dateField = new JTextField(10); // Ngày theo định dạng "yyyy-MM-dd"
        JTextField startField = new JTextField(5);
        JTextField endField = new JTextField(5);
        JTextArea descriptionArea = new JTextArea(5, 20);
        
        JPanel panel = new JPanel(new GridLayout(0,1));
        panel.add(new JLabel("Tiêu đề Sự kiện:"));
        panel.add(titleField);
        panel.add(new JLabel("Ngày (yyyy-MM-dd):"));
        panel.add(dateField);
        panel.add(new JLabel("Thời gian bắt đầu (hh:mm):"));
        panel.add(startField);
        panel.add(new JLabel("Thời gian kết thúc (hh:mm):"));
        panel.add(endField);
        panel.add(new JLabel("Mô tả:"));
        panel.add(new JScrollPane(descriptionArea));
        
        int result = JOptionPane.showConfirmDialog(this, panel, "Tạo Sự kiện mới", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String title = titleField.getText().trim();
            String dateStr = dateField.getText().trim();
            String startTime = startField.getText().trim();
            String endTime = endField.getText().trim();
            String description = descriptionArea.getText().trim();
            
            if (!title.isEmpty() && !dateStr.isEmpty() && !startTime.isEmpty() && !endTime.isEmpty()) {
                try {
                    java.util.Date eventDate = java.sql.Date.valueOf(dateStr); // Chuyển đổi từ String sang Date
                    CalendarEvent event = new CalendarEvent();
                    event.setEventTitle(title);
                    event.setEventDate(eventDate);
                    event.setStartTime(startTime);
                    event.setEndTime(endTime);
                    event.setDescription(description);
                    
                    CalendarEventDAO dao = new CalendarEventDAO();
                    if (dao.addCalendarEvent(event)) {
                        JOptionPane.showMessageDialog(this, "Sự kiện đã được tạo!");
                        loadEvents();
                    } else {
                        JOptionPane.showMessageDialog(this, "Có lỗi khi tạo sự kiện.");
                    }
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(this, "Định dạng ngày không hợp lệ. Vui lòng nhập theo dạng yyyy-MM-dd.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Các trường bắt buộc không được để trống!");
            }
        }
    }
    
    class EventTableModel extends AbstractTableModel {
        private final String[] columns = {"ID", "Tiêu đề Sự kiện", "Ngày", "Bắt đầu", "Kết thúc", "Mô tả"};
        private List<CalendarEvent> events;
        
        public void setEvents(List<CalendarEvent> events) {
            this.events = events;
            fireTableDataChanged();
        }
        
        @Override
        public int getRowCount() {
            return events == null ? 0 : events.size();
        }
        
        @Override
        public int getColumnCount() {
            return columns.length;
        }
        
        @Override
        public String getColumnName(int column) {
            return columns[column];
        }
        
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            CalendarEvent event = events.get(rowIndex);
            switch (columnIndex) {
                case 0: return event.getId();
                case 1: return event.getEventTitle();
                case 2: return event.getEventDate();
                case 3: return event.getStartTime();
                case 4: return event.getEndTime();
                case 5: return event.getDescription();
                default: return "";
            }
        }
    }
}
