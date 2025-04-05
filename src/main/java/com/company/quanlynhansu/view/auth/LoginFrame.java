package com.company.quanlynhansu.view.auth;

import com.company.quanlynhansu.dao.UserDAO;
import com.company.quanlynhansu.model.User;
import com.company.quanlynhansu.view.admin.AdminDashboardFrame;
import com.company.quanlynhansu.view.employee.EmployeeDashboardFrame;
// Nếu bạn có triển khai giao diện Manager, import nó, ví dụ:
import com.company.quanlynhansu.view.manager.ManagerDashboardFrame;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private static final Logger logger = LoggerFactory.getLogger(LoginFrame.class);
    
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginFrame() {
        setTitle("Đăng nhập Hệ thống");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Đăng nhập");

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(usernameLabel, gbc);
        gbc.gridx = 1;
        panel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(loginButton, gbc);

        add(panel);
        
        // Thêm sự kiện nút đăng nhập
        loginButton.addActionListener(e -> performLogin());
    }
    
    private void performLogin() {
        String username = usernameField.getText().trim();
        String plainPassword = new String(passwordField.getPassword()).trim();

        logger.info("Người dùng đăng nhập với username: {}", username);

        if (username.isEmpty() || plainPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
            logger.warn("Đăng nhập thất bại - thiếu thông tin đầu vào.");
            return;
        }
        
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserByUsername(username);
        if (user != null) {
            logger.info("Đã tìm thấy user trong database với username: {}", username);
            if (BCrypt.checkpw(plainPassword, user.getPassword())) {
                logger.info("Xác thực mật khẩu thành công cho user: {}", username);
                JOptionPane.showMessageDialog(this, "Đăng nhập thành công với vai trò: " + user.getRole());
                // Phân hướng dashboard theo vai trò của người dùng
                if ("ADMIN".equalsIgnoreCase(user.getRole()) || "HR".equalsIgnoreCase(user.getRole())) {
                    new AdminDashboardFrame().setVisible(true);
                } else if ("EMPLOYEE".equalsIgnoreCase(user.getRole())) {
                    new EmployeeDashboardFrame().setVisible(true);
                } else if ("MANAGER".equalsIgnoreCase(user.getRole())) {
                    // Nếu đã có ManagerDashboardFrame, mở nó ra
                    new ManagerDashboardFrame().setVisible(true);
                    JOptionPane.showMessageDialog(this, "Chức năng cho vai trò MANAGER chưa được triển khai, vui lòng kiểm tra lại.");
                } else {
                    JOptionPane.showMessageDialog(this, "Vai trò không được hỗ trợ!");
                }
                dispose();
            } else {
                logger.error("Sai mật khẩu cho user: {}", username);
                JOptionPane.showMessageDialog(this, "Sai mật khẩu, vui lòng kiểm tra lại!");
            }
        } else {
            logger.error("Không tìm thấy tài khoản với username: {}", username);
            JOptionPane.showMessageDialog(this, "Không tìm thấy tài khoản!");
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}
