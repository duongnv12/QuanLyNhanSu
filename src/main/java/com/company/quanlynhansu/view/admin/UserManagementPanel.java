package com.company.quanlynhansu.view.admin;

import com.company.quanlynhansu.controller.UserController;
import com.company.quanlynhansu.model.User;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.List;

public class UserManagementPanel extends JPanel {
    private JTable userTable;
    private UserTableModel tableModel;
    private JButton addUserButton, editUserButton, deleteUserButton, refreshButton;
    private JTextField searchField;
    private JButton searchButton, clearSearchButton;
    
    public UserManagementPanel() {
        initComponents();
        loadUsers();
    }
    
    private void initComponents() {
        setLayout(new BorderLayout());
        
        tableModel = new UserTableModel();
        userTable = new JTable(tableModel);
        add(new JScrollPane(userTable), BorderLayout.CENTER);
        
        // Panel chứa các nút chức năng
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        addUserButton = new JButton("Thêm người dùng");
        editUserButton = new JButton("Sửa");
        deleteUserButton = new JButton("Xóa");
        refreshButton = new JButton("Làm mới");
        
        btnPanel.add(addUserButton);
        btnPanel.add(editUserButton);
        btnPanel.add(deleteUserButton);
        btnPanel.add(refreshButton);
        
        // Panel chức năng tìm kiếm
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        searchField = new JTextField(20);
        searchButton = new JButton("Tìm kiếm");
        clearSearchButton = new JButton("Clear");
        searchPanel.add(new JLabel("Tìm kiếm:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(clearSearchButton);
        
        // Top panel kết hợp các chức năng
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(btnPanel, BorderLayout.WEST);
        topPanel.add(searchPanel, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);
        
        // Đăng ký sự kiện cho các nút
        addUserButton.addActionListener(e -> onAddUser());
        editUserButton.addActionListener(e -> onEditUser());
        deleteUserButton.addActionListener(e -> onDeleteUser());
        refreshButton.addActionListener(e -> loadUsers());
        searchButton.addActionListener(e -> onSearch());
        clearSearchButton.addActionListener(e -> onClearSearch());
    }
    
    private void loadUsers() {
        List<User> userList = UserController.getInstance().getAllUsers();
        tableModel.setUsers(userList);
    }
    
    private void onAddUser() {
        // Giao diện nhập dữ liệu đơn giản để thêm người dùng
        JTextField usernameField = new JTextField(15);
        JTextField passwordField = new JTextField(15);
        JTextField roleField = new JTextField(10);
        JPanel panel = new JPanel(new GridLayout(0,1));
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(new JLabel("Role:"));
        panel.add(roleField);
        int result = JOptionPane.showConfirmDialog(this, panel, "Thêm người dùng", JOptionPane.OK_CANCEL_OPTION);
        if(result == JOptionPane.OK_OPTION) {
            String username = usernameField.getText().trim();
            String password = passwordField.getText().trim();
            String role = roleField.getText().trim();
            if(username.isEmpty() || password.isEmpty() || role.isEmpty()){
                JOptionPane.showMessageDialog(this, "Các trường không được để trống!");
                return;
            }
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setPassword(password);  // Lưu ý: trong thực tế cần mã hoá password
            newUser.setRole(role);
            boolean success = UserController.getInstance().addUser(newUser);
            JOptionPane.showMessageDialog(this, success ? "Thêm người dùng thành công" : "Thêm người dùng thất bại");
            loadUsers();
        }
    }
    
    private void onEditUser() {
        int row = userTable.getSelectedRow();
        if(row < 0) {
            JOptionPane.showMessageDialog(this, "Chọn người dùng cần sửa!");
            return;
        }
        User user = tableModel.getUserAt(row);
        JTextField usernameField = new JTextField(user.getUsername(),15);
        JTextField passwordField = new JTextField(user.getPassword(),15);
        JTextField roleField = new JTextField(user.getRole(),10);
        JPanel panel = new JPanel(new GridLayout(0,1));
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(new JLabel("Role:"));
        panel.add(roleField);
        int result = JOptionPane.showConfirmDialog(this, panel, "Sửa người dùng", JOptionPane.OK_CANCEL_OPTION);
        if(result == JOptionPane.OK_OPTION){
            user.setUsername(usernameField.getText().trim());
            user.setPassword(passwordField.getText().trim());
            user.setRole(roleField.getText().trim());
            boolean success = UserController.getInstance().updateUser(user);
            JOptionPane.showMessageDialog(this, success ? "Sửa người dùng thành công" : "Sửa người dùng thất bại");
            loadUsers();
        }
    }
    
    private void onDeleteUser() {
        int row = userTable.getSelectedRow();
        if(row < 0) {
            JOptionPane.showMessageDialog(this, "Chọn người dùng cần xóa!");
            return;
        }
        User user = tableModel.getUserAt(row);
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn xóa người dùng: " + user.getUsername() + "?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if(confirm == JOptionPane.YES_OPTION){
            boolean success = UserController.getInstance().deleteUser(user.getId());
            JOptionPane.showMessageDialog(this, success ? "Xóa người dùng thành công" : "Xóa người dùng thất bại");
            loadUsers();
        }
    }
    
    private void onSearch() {
        String keyword = searchField.getText().trim();
        if(keyword.isEmpty()){
            JOptionPane.showMessageDialog(this, "Nhập từ khóa cần tìm!");
            return;
        }
        // Tìm kiếm đơn giản theo username tại đây (có thể gọi controller nếu có chức năng tìm kiếm chuyên biệt)
        List<User> allUsers = UserController.getInstance().getAllUsers();
        List<User> filtered = new java.util.ArrayList<>();
        for(User user : allUsers) {
            if(user.getUsername().toLowerCase().contains(keyword.toLowerCase())){
                filtered.add(user);
            }
        }
        tableModel.setUsers(filtered);
    }
    
    private void onClearSearch() {
        searchField.setText("");
        loadUsers();
    }
    
    private class UserTableModel extends AbstractTableModel {
        private final String[] columns = {"ID", "Username", "Role"};
        private List<User> users;
        
        public void setUsers(List<User> users) {
            this.users = users;
            fireTableDataChanged();
        }
        
        public User getUserAt(int row) {
            return users.get(row);
        }
        
        @Override
        public int getRowCount() {
            return users == null ? 0 : users.size();
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
            User user = users.get(rowIndex);
            switch(columnIndex){
                case 0: return user.getId();
                case 1: return user.getUsername();
                case 2: return user.getRole();
                default: return "";
            }
        }
    }
}
