package com.company.quanlynhansu.view.admin;

import com.company.quanlynhansu.view.internal.InternalNotificationPanel;
import com.company.quanlynhansu.view.internal.CalendarPanel;
import javax.swing.*;
import java.awt.*;

public class NotificationCalendarPanel extends JPanel {
    public NotificationCalendarPanel() {
        setLayout(new BorderLayout());
        // Sử dụng JTabbedPane để chứa 2 panel riêng biệt: Thông báo và Lịch làm việc
        JTabbedPane internalTabbedPane = new JTabbedPane();
        internalTabbedPane.addTab("Thông báo", new InternalNotificationPanel());
        internalTabbedPane.addTab("Lịch làm việc", new CalendarPanel());
        add(internalTabbedPane, BorderLayout.CENTER);
    }
}
