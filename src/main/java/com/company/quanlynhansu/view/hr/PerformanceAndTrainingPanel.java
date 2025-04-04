package com.company.quanlynhansu.view.hr;

import javax.swing.*;
import java.awt.*;

public class PerformanceAndTrainingPanel extends JPanel {

    public PerformanceAndTrainingPanel() {
        setLayout(new BorderLayout());
        JTabbedPane tabbedPane = new JTabbedPane();

        // Tab đánh giá hiệu suất
        PerformanceManagementPanel performancePanel = new PerformanceManagementPanel();
        tabbedPane.addTab("Đánh giá hiệu suất", performancePanel);

        // Tab đào tạo nhân sự
        TrainingManagementPanel trainingPanel = new TrainingManagementPanel();
        tabbedPane.addTab("Đào tạo nhân sự", trainingPanel);

        add(tabbedPane, BorderLayout.CENTER);
    }
}
