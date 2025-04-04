package com.company.quanlynhansu.view.timekeeping;

import com.company.quanlynhansu.dao.TimekeepingDAO;
import com.company.quanlynhansu.model.Timekeeping;

import javax.swing.*;
import java.awt.*;
import java.sql.Timestamp;
import java.util.Date;

public class TimekeepingFrame extends JFrame {
    private JButton checkInButton;
    private JButton checkOutButton;
    private int employeeId; // Giả sử đã xác định được ID của nhân viên đang đăng nhập

    public TimekeepingFrame(int employeeId) {
        this.employeeId = employeeId;
        setTitle("Chấm công - Nhân viên ID: " + employeeId);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        checkInButton = new JButton("Check In");
        checkOutButton = new JButton("Check Out");

        JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
        panel.add(checkInButton);
        panel.add(checkOutButton);
        add(panel);

        checkInButton.addActionListener(e -> handleCheckIn());
        checkOutButton.addActionListener(e -> handleCheckOut());
    }

    private void handleCheckIn() {
        TimekeepingDAO dao = new TimekeepingDAO();
        // Kiểm tra xem đã điểm danh vào chưa
        if (dao.getTodayRecord(employeeId) != null) {
            JOptionPane.showMessageDialog(this, "Bạn đã điểm danh vào ngày hôm nay!");
            return;
        }
        Timekeeping record = new Timekeeping();
        record.setEmployeeId(employeeId);
        record.setCheckIn(new Timestamp(new Date().getTime()));
        if (dao.insertCheckIn(record)) {
            JOptionPane.showMessageDialog(this, "Check In thành công!");
        } else {
            JOptionPane.showMessageDialog(this, "Xảy ra lỗi khi Check In!");
        }
    }

    private void handleCheckOut() {
        TimekeepingDAO dao = new TimekeepingDAO();
        Timekeeping record = dao.getTodayRecord(employeeId);
        if (record == null) {
            JOptionPane.showMessageDialog(this, "Bạn cần Check In trước khi Check Out!");
            return;
        }
        if (record.getCheckOut() != null) {
            JOptionPane.showMessageDialog(this, "Bạn đã Check Out!");
            return;
        }
        record.setCheckOut(new Timestamp(new Date().getTime()));
        if (dao.updateCheckOut(record)) {
            JOptionPane.showMessageDialog(this, "Check Out thành công!");
        } else {
            JOptionPane.showMessageDialog(this, "Xảy ra lỗi khi Check Out!");
        }
    }
    
    // Main để test giao diện chấm công
    public static void main(String[] args) {
        // Giả sử employeeId = 1
        SwingUtilities.invokeLater(() -> new TimekeepingFrame(3).setVisible(true));
    }
}
