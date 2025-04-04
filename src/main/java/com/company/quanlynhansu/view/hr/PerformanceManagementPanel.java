package com.company.quanlynhansu.view.hr;

import com.company.quanlynhansu.controller.PerformanceEvaluationController;
import com.company.quanlynhansu.model.PerformanceEvaluation;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.List;

public class PerformanceManagementPanel extends JPanel {
    private JTable evaluationTable;
    private EvaluationTableModel tableModel;
    private JButton addEvaluationButton;
    
    public PerformanceManagementPanel() {
        initComponents();
        loadEvaluations();
    }
    
    private void initComponents() {
        setLayout(new BorderLayout());
        
        tableModel = new EvaluationTableModel();
        evaluationTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(evaluationTable);
        add(scrollPane, BorderLayout.CENTER);
        
        addEvaluationButton = new JButton("Thêm đánh giá mới");
        addEvaluationButton.addActionListener(e -> {
            // Hiển thị form nhập đánh giá (có thể là dialog) – code chi tiết tùy theo yêu cầu
            // Ví dụ: new PerformanceEvaluationDialog(parent, null).setVisible(true);
            // Sau đó làm mới bảng:
            loadEvaluations();
        });
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnPanel.add(addEvaluationButton);
        add(btnPanel, BorderLayout.NORTH);
    }
    
    private void loadEvaluations() {
        // Giả sử có PerformanceEvaluationController với phương thức getAllEvaluations()
        List<PerformanceEvaluation> evaluations = PerformanceEvaluationController.getInstance().getAllEvaluations();
        tableModel.setEvaluations(evaluations);
    }
    
    // Table model hiển thị các cột: Employee ID, Kỳ đánh giá, Người đánh giá, Điểm, Ghi chú, Ngày đánh giá
    public class EvaluationTableModel extends AbstractTableModel {
        private final String[] columns = {"Employee ID", "Kỳ đánh giá", "Người đánh giá", "Điểm", "Ghi chú", "Ngày đánh giá"};
        private List<PerformanceEvaluation> evaluations;
        
        public void setEvaluations(List<PerformanceEvaluation> evaluations) {
            this.evaluations = evaluations;
            fireTableDataChanged();
        }
        
        @Override
        public int getRowCount() {
            return evaluations == null ? 0 : evaluations.size();
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
            PerformanceEvaluation eval = evaluations.get(rowIndex);
            switch (columnIndex) {
                case 0: return eval.getEmployeeId();
                case 1: return eval.getEvaluationPeriod();
                case 2: return eval.getEvaluator();
                case 3: return eval.getRating();
                case 4: return eval.getComments();
                case 5: return eval.getEvaluationDate();
                default: return "";
            }
        }
    }
}
