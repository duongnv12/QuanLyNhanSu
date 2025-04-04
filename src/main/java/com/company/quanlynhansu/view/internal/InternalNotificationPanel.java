package com.company.quanlynhansu.view.internal;

import com.company.quanlynhansu.dao.NotificationDAO;
import com.company.quanlynhansu.model.Notification;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.List;

public class InternalNotificationPanel extends JPanel {
    private JTable notificationTable;
    private NotificationTableModel tableModel;
    private JButton newNotificationButton;
    
    public InternalNotificationPanel() {
        initComponents();
        loadNotifications();
    }
    
    private void initComponents() {
        setLayout(new BorderLayout());
        
        tableModel = new NotificationTableModel();
        notificationTable = new JTable(tableModel);
        add(new JScrollPane(notificationTable), BorderLayout.CENTER);
        
        newNotificationButton = new JButton("Tạo thông báo mới");
        newNotificationButton.addActionListener(e -> openNewNotificationDialog());
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(newNotificationButton);
        add(topPanel, BorderLayout.NORTH);
    }
    
    private void loadNotifications() {
        NotificationDAO dao = new NotificationDAO();
        List<Notification> notifications = dao.getAllNotifications();
        tableModel.setNotifications(notifications);
    }
    
    private void openNewNotificationDialog() {
        JTextField titleField = new JTextField(20);
        JTextArea messageArea = new JTextArea(5, 20);
        JPanel panel = new JPanel(new GridLayout(0,1));
        panel.add(new JLabel("Tiêu đề:"));
        panel.add(titleField);
        panel.add(new JLabel("Nội dung:"));
        panel.add(new JScrollPane(messageArea));
        
        int result = JOptionPane.showConfirmDialog(this, panel, "Tạo thông báo mới", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String title = titleField.getText().trim();
            String message = messageArea.getText().trim();
            if (!title.isEmpty() && !message.isEmpty()) {
                Notification notification = new Notification();
                notification.setTitle(title);
                notification.setMessage(message);
                notification.setCreatedAt(new java.util.Date());
                NotificationDAO dao = new NotificationDAO();
                if (dao.addNotification(notification)) {
                    JOptionPane.showMessageDialog(this, "Thông báo đã được tạo!");
                    loadNotifications();
                } else {
                    JOptionPane.showMessageDialog(this, "Có lỗi khi tạo thông báo.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Tiêu đề và nội dung không được để trống!");
            }
        }
    }
    
    // Inner table model để hiển thị thông báo
    public class NotificationTableModel extends AbstractTableModel {
        private final String[] columns = {"ID", "Tiêu đề", "Nội dung", "Ngày tạo"};
        private List<Notification> notifications;
        
        public void setNotifications(List<Notification> notifications) {
            this.notifications = notifications;
            fireTableDataChanged();
        }
        
        @Override
        public int getRowCount() {
            return notifications == null ? 0 : notifications.size();
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
            Notification n = notifications.get(rowIndex);
            switch (columnIndex) {
                case 0: return n.getId();
                case 1: return n.getTitle();
                case 2: return n.getMessage();
                case 3: return n.getCreatedAt();
                default: return "";
            }
        }
    }
}
