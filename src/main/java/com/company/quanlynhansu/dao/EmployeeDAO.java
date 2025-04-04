package com.company.quanlynhansu.dao;

import com.company.quanlynhansu.model.Employee;
import com.company.quanlynhansu.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    // Lấy danh sách tất cả nhân viên từ bảng employees
    public List<Employee> getAllEmployees() {
        List<Employee> employeeList = new ArrayList<>();
        String sql = "SELECT id, name, position, salary FROM employees ORDER BY id ASC"; // sắp xếp theo thứ tự tăng dần của id
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()){
                Employee emp = new Employee();
                emp.setId(rs.getInt("id"));
                emp.setName(rs.getString("name"));
                emp.setPosition(rs.getString("position"));
                emp.setSalary(rs.getDouble("salary"));
                employeeList.add(emp);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return employeeList;
    }
    

    // Thêm nhân viên
    public boolean addEmployee(Employee employee) {
        String sql = "INSERT INTO employees (id, name, position, salary) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, employee.getId());
            pstmt.setString(2, employee.getName());
            pstmt.setString(3, employee.getPosition());
            pstmt.setDouble(4, employee.getSalary());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Cập nhật thông tin nhân viên
    public boolean updateEmployee(Employee employee) {
        String sql = "UPDATE employees SET name = ?, position = ?, salary = ? WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, employee.getName());
            pstmt.setString(2, employee.getPosition());
            pstmt.setDouble(3, employee.getSalary());
            pstmt.setInt(4, employee.getId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Xoá nhân viên khỏi database
    public boolean deleteEmployee(int employeeId) {
        String sql = "DELETE FROM employees WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, employeeId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteAllEmployees() {
        String deleteTimekeepingSql = "DELETE FROM timekeeping";
        String deleteEmployeesSql = "DELETE FROM employees";
        String resetSequenceSql = "ALTER SEQUENCE employees_id_seq RESTART WITH 1";
        
        try (Connection conn = DatabaseUtil.getConnection()) {
            // Bắt đầu transaction
            conn.setAutoCommit(false);
            try (Statement stmt = conn.createStatement()) {
                // Xóa các bản ghi liên quan trong bảng timekeeping trước
                stmt.executeUpdate(deleteTimekeepingSql);
                // Xóa toàn bộ dữ liệu trong bảng employees
                int rowsAffected = stmt.executeUpdate(deleteEmployeesSql);
                // Reset sequence của bảng employees, đảm bảo id được sắp xếp lại
                stmt.executeUpdate(resetSequenceSql);
                
                conn.commit();
                System.out.println("Đã xóa " + rowsAffected + " dòng trong bảng employees và reset sequence id.");
                return true;
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
                return false;
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }    
    
    // Tìm kiếm nhân viên theo tên
    public List<Employee> searchEmployees(String keyword) {
        List<Employee> result = new ArrayList<>();
        String sql = "SELECT id, name, position, salary FROM employees " +
                    "WHERE LOWER(name) LIKE ? OR LOWER(position) LIKE ?";
        try (Connection conn = DatabaseUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            String query = "%" + keyword.toLowerCase() + "%";
            pstmt.setString(1, query);
            pstmt.setString(2, query);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Employee emp = new Employee(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("position"),
                            rs.getDouble("salary")
                    );
                    result.add(emp);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
