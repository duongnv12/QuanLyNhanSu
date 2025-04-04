package com.company.quanlynhansu.controller;

import com.company.quanlynhansu.dao.EmployeeDAO;
import com.company.quanlynhansu.model.Employee;

import java.util.List;

public class EmployeeController {
    private static EmployeeController instance;
    private EmployeeDAO employeeDAO;

    private EmployeeController() {
        employeeDAO = new EmployeeDAO();
    }

    public static EmployeeController getInstance() {
        if (instance == null) {
            instance = new EmployeeController();
        }
        return instance;
    }

    public List<Employee> getEmployees() {
        return employeeDAO.getAllEmployees();
    }

    public boolean addEmployee(Employee employee) {
        return employeeDAO.addEmployee(employee);
    }

    public boolean updateEmployee(Employee employee) {
        return employeeDAO.updateEmployee(employee);
    }

    public boolean deleteEmployee(int employeeId) {
        return employeeDAO.deleteEmployee(employeeId);
    }
    public List<Employee> searchEmployees(String keyword) {
        return employeeDAO.searchEmployees(keyword);
    }
}
