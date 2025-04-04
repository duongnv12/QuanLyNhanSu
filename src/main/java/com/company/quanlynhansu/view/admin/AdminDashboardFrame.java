package com.company.quanlynhansu.view.admin;

import com.company.quanlynhansu.view.reports.ReportStatisticsFrame;
import com.company.quanlynhansu.view.reports.HRTimekeepingReportFrame;
import com.company.quanlynhansu.view.hr.PerformanceAndTrainingPanel;
import javax.swing.*;
import java.awt.*;

public class AdminDashboardFrame extends JFrame {

    private JTabbedPane tabbedPane;
    // Các panel đã có
    private EmployeeManagementPanel employeeManagementPanel;
    private UserManagementPanel userManagementPanel;
    private ReportStatisticsPanel reportStatisticsPanel;
    private HRTimekeepingReportPanel hrTimekeepingReportPanel;
    private PerformanceAndTrainingPanel performanceAndTrainingPanel;
    private NotificationCalendarPanel notificationCalendarPanel;
    private LeaveManagementPanel leaveManagementPanel;

    public AdminDashboardFrame() {
        setTitle("Dashboard Quản Lý Nhân Sự");
        setSize(1300, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        tabbedPane = new JTabbedPane();
        
        // Tab 1: Quản lý nhân sự
        employeeManagementPanel = new EmployeeManagementPanel();
        tabbedPane.addTab("Quản lý nhân sự", employeeManagementPanel);
        
        // Tab 2: Quản lý người dùng
        userManagementPanel = new UserManagementPanel();
        tabbedPane.addTab("Quản lý người dùng", userManagementPanel);
        
        // Tab 3: Báo cáo thống kê - bọc ReportStatisticsFrame thành panel
        reportStatisticsPanel = new ReportStatisticsPanel();
        tabbedPane.addTab("Báo cáo thống kê", reportStatisticsPanel);
        
        // Tab 4: Báo cáo chấm công - bọc HRTimekeepingReportFrame thành panel
        hrTimekeepingReportPanel = new HRTimekeepingReportPanel();
        tabbedPane.addTab("Báo cáo chấm công", hrTimekeepingReportPanel);
        
        // Tab 5: Hiệu suất & Đào tạo
        performanceAndTrainingPanel = new PerformanceAndTrainingPanel();
        tabbedPane.addTab("Hiệu suất & Đào tạo", performanceAndTrainingPanel);
        
        // Tab 6: Thông báo & Lịch - sử dụng NotificationCalendarPanel đã xây dựng
        notificationCalendarPanel = new NotificationCalendarPanel();
        tabbedPane.addTab("Thông báo & Lịch", notificationCalendarPanel);
        
        // Tab 7: Quản lý nghỉ phép
        leaveManagementPanel = new LeaveManagementPanel();
        tabbedPane.addTab("Quản lý nghỉ phép", leaveManagementPanel);
        
        add(tabbedPane, BorderLayout.CENTER);
    }

    // Các lớp inner dùng để bọc giao diện của ReportStatisticsFrame
    public class ReportStatisticsPanel extends JPanel {
        public ReportStatisticsPanel() {
            setLayout(new BorderLayout());
            ReportStatisticsFrame statisticsFrame = new ReportStatisticsFrame();
            statisticsFrame.setVisible(false);
            add(statisticsFrame.getContentPane(), BorderLayout.CENTER);
        }
    }

    // Các lớp inner dùng để bọc giao diện của HRTimekeepingReportFrame
    public class HRTimekeepingReportPanel extends JPanel {
        public HRTimekeepingReportPanel() {
            setLayout(new BorderLayout());
            HRTimekeepingReportFrame timekeepingFrame = new HRTimekeepingReportFrame();
            timekeepingFrame.setVisible(false);
            add(timekeepingFrame.getContentPane(), BorderLayout.CENTER);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdminDashboardFrame().setVisible(true));
    }
}
