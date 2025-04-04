package com.company.quanlynhansu.view.hr;

import com.company.quanlynhansu.controller.TrainingRecordController;
import com.company.quanlynhansu.model.TrainingRecord;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.List;

public class TrainingManagementPanel extends JPanel {
    private JTable trainingTable;
    private TrainingTableModel tableModel;
    private JButton addTrainingButton;
    
    public TrainingManagementPanel() {
        initComponents();
        loadTrainingRecords();
    }
    
    private void initComponents() {
        setLayout(new BorderLayout());
        
        tableModel = new TrainingTableModel();
        trainingTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(trainingTable);
        add(scrollPane, BorderLayout.CENTER);
        
        addTrainingButton = new JButton("Thêm khóa đào tạo mới");
        addTrainingButton.addActionListener(e -> {
            // Hiển thị form nhập đào tạo, sau đó làm mới bảng:
            loadTrainingRecords();
        });
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnPanel.add(addTrainingButton);
        add(btnPanel, BorderLayout.NORTH);
    }
    
    private void loadTrainingRecords() {
        // Giả sử có TrainingRecordController với phương thức getAllTrainingRecords()
        List<TrainingRecord> records = TrainingRecordController.getInstance().getAllTrainingRecords();
        tableModel.setTrainingRecords(records);
    }
    
    public class TrainingTableModel extends AbstractTableModel {
        private final String[] columns = {"Employee ID", "Khóa đào tạo", "Ngày đào tạo", "Chứng chỉ", "Ghi chú"};
        private List<TrainingRecord> records;
        
        public void setTrainingRecords(List<TrainingRecord> records) {
            this.records = records;
            fireTableDataChanged();
        }
        
        @Override
        public int getRowCount() {
            return records == null ? 0 : records.size();
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
            TrainingRecord rec = records.get(rowIndex);
            switch (columnIndex) {
                case 0: return rec.getEmployeeId();
                case 1: return rec.getTrainingCourse();
                case 2: return rec.getTrainingDate();
                case 3: return rec.getCertificate();
                case 4: return rec.getComments();
                default: return "";
            }
        }
    }
}
