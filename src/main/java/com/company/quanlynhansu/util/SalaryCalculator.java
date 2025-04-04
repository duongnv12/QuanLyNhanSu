package com.company.quanlynhansu.util;

public class SalaryCalculator {
    // Tính lương dựa trên giờ làm việc và mức lương giờ cơ bản
    public static double calculateSalary(double hourlyRate, double totalHoursWorked) {
        return hourlyRate * totalHoursWorked;
    }
}

