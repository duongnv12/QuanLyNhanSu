package com.company.quanlynhansu.util;

import com.company.quanlynhansu.model.Employee;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class CSVImporter {

    /**
     * Đọc file CSV và trả về danh sách các đối tượng Employee.
     * Giả sử file có header ở hàng đầu tiên với định dạng:
     * ID,Tên,Chức vụ,Lương
     */
    public static List<Employee> importEmployees(File file) {
        List<Employee> employees = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(file.toPath())) {
            String line;
            boolean isFirstLine = true;
            int lineNumber = 0;
            while ((line = br.readLine()) != null) {
                lineNumber++;
                // Bỏ qua dòng header
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                // Xử lý nếu dòng rỗng
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] tokens = line.split(",");
                if (tokens.length < 4) {
                    System.err.println("Dữ liệu không đầy đủ ở dòng " + lineNumber + ": " + line);
                    continue;
                }
                try {
                    int id = Integer.parseInt(tokens[0].trim());
                    String name = tokens[1].trim();
                    String position = tokens[2].trim();
                    double salary = Double.parseDouble(tokens[3].trim());
                    // Kiểm tra bắt buộc không dữ liệu trống
                    if (name.isEmpty() || position.isEmpty()) {
                        System.err.println("Thiếu thông tin bắt buộc ở dòng " + lineNumber + ": " + line);
                        continue;
                    }
                    Employee employee = new Employee(id, name, position, salary);
                    employees.add(employee);
                } catch (NumberFormatException ex) {
                    System.err.println("Lỗi chuyển đổi dữ liệu ở dòng " + lineNumber + ": " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employees;
    }
}
