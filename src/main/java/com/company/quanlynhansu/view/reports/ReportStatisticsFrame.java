package com.company.quanlynhansu.view.reports;

import com.company.quanlynhansu.controller.EmployeeController;
import com.company.quanlynhansu.model.Employee;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportStatisticsFrame extends JFrame {

    public ReportStatisticsFrame() {
        setTitle("Báo cáo Thống kê Nhân sự");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        // Lấy toàn bộ dữ liệu nhân viên từ hệ thống
        List<Employee> employees = EmployeeController.getInstance().getEmployees();

        // Tính toán thống kê:
        // 1. Số lượng nhân viên theo chức vụ
        Map<String, Integer> countByPosition = new HashMap<>();
        // 2. Tính tổng lương theo chức vụ (và số lượng nhân viên theo chức vụ để tính trung bình)
        Map<String, Double> sumSalaryByPosition = new HashMap<>();
        Map<String, Integer> countSalaryByPosition = new HashMap<>();

        for (Employee emp : employees) {
            String position = emp.getPosition();
            countByPosition.put(position, countByPosition.getOrDefault(position, 0) + 1);
            sumSalaryByPosition.put(position, sumSalaryByPosition.getOrDefault(position, 0.0) + emp.getSalary());
            countSalaryByPosition.put(position, countSalaryByPosition.getOrDefault(position, 0) + 1);
        }

        // Tính lương trung bình theo chức vụ
        Map<String, Double> averageSalaryByPosition = new HashMap<>();
        for (String position : sumSalaryByPosition.keySet()) {
            double avg = sumSalaryByPosition.get(position) / countSalaryByPosition.get(position);
            averageSalaryByPosition.put(position, avg);
        }

        // Tạo dataset cho biểu đồ số lượng nhân viên theo chức vụ
        DefaultCategoryDataset countDataset = new DefaultCategoryDataset();
        for (Map.Entry<String, Integer> entry : countByPosition.entrySet()) {
            countDataset.addValue(entry.getValue(), "Nhân viên", entry.getKey());
        }
        JFreeChart countChart = ChartFactory.createBarChart(
                "Số lượng nhân viên theo chức vụ",  // tiêu đề biểu đồ
                "Chức vụ",                         // tên trục X
                "Số lượng",                        // tên trục Y
                countDataset
        );

        // Tạo dataset cho biểu đồ mức lương trung bình theo chức vụ
        DefaultCategoryDataset salaryDataset = new DefaultCategoryDataset();
        for (Map.Entry<String, Double> entry : averageSalaryByPosition.entrySet()) {
            salaryDataset.addValue(entry.getValue(), "Lương trung bình", entry.getKey());
        }
        JFreeChart salaryChart = ChartFactory.createBarChart(
                "Mức lương trung bình theo chức vụ",
                "Chức vụ",
                "Lương trung bình",
                salaryDataset
        );

        // Tạo các ChartPanel để hiển thị biểu đồ
        ChartPanel countChartPanel = new ChartPanel(countChart);
        ChartPanel salaryChartPanel = new ChartPanel(salaryChart);

        // Sử dụng JTabbedPane để hiển thị nhiều báo cáo
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Số lượng", countChartPanel);
        tabbedPane.addTab("Lương trung bình", salaryChartPanel);

        getContentPane().add(tabbedPane, BorderLayout.CENTER);
    }
}
