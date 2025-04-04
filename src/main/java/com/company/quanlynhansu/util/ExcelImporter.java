package com.company.quanlynhansu.util;

import com.company.quanlynhansu.model.Employee;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelImporter {

    /**
     * Đọc file Excel và trả về danh sách nhân viên.
     * Giả sử file có dạng:
     * Row 0: Header ("ID", "Tên", "Chức vụ", "Lương")
     * Row 1 trở đi: dữ liệu nhân viên
     */
    public static List<Employee> importEmployees(File file) {
        List<Employee> employees = new ArrayList<>();
        try (Workbook workbook = WorkbookFactory.create(file)) {
            // Lấy sheet đầu tiên
            Sheet sheet = workbook.getSheetAt(0);
            // Dữ liệu bắt đầu từ hàng thứ 2 (index 1)
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    // Lấy dữ liệu từ các cột với chính sách tạo cell rỗng nếu thiếu
                    Cell cellId = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    Cell cellName = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    Cell cellPosition = row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    Cell cellSalary = row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

                    int id = (int) cellId.getNumericCellValue();
                    String name = cellName.getStringCellValue();
                    String position = cellPosition.getStringCellValue();
                    double salary = cellSalary.getNumericCellValue();

                    Employee employee = new Employee(id, name, position, salary);
                    employees.add(employee);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employees;
    }
}
