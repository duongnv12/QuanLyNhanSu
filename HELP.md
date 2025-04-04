
---


# Quản Lý Nhân Sự

## Giới thiệu

"Quản Lý Nhân Sự" là một hệ thống quản trị toàn diện dành cho doanh nghiệp, giúp quản lý thông tin nhân sự, chấm công, báo cáo, đánh giá hiệu suất, đào tạo, cũng như các module nội bộ như thông báo và lịch làm việc.  
Hệ thống được xây dựng bằng Java (với giao diện Swing) và sử dụng cơ sở dữ liệu PostgreSQL để lưu trữ dữ liệu. Ngoài ra, dự án còn tích hợp các chức năng gửi Email và SMS tự động nhằm đảm bảo thông tin được chuyển giao kịp thời giữa các bên.

## Tính năng chính

- **Quản lý nhân sự:**  
  CRUD cho nhân viên, nhập dữ liệu từ file Excel/CSV, tự động sắp xếp và reset ID khi cần.

- **Quản lý người dùng & phân quyền:**  
  Quản lý tài khoản người dùng (User) theo các vai trò như ADMIN, HR, EMPLOYEE.

- **Chấm công & Báo cáo chấm công:**  
  Ghi nhận thông tin check-in, check-out và hiển thị báo cáo trạng thái chấm công.

- **Báo cáo thống kê:**  
  Thống kê số liệu nhân sự theo các chỉ số như mức lương, số lượng nhân viên theo phòng ban,...

- **Hiệu suất & Đào tạo:**  
  Quản lý lượt đánh giá hiệu suất và thông tin đào tạo của nhân viên.

- **Quản lý nghỉ phép:**  
  Yêu cầu xin nghỉ, duyệt yêu cầu nghỉ (approve/reject) của nhân viên.

- **Thông báo nội bộ & Lịch làm việc:**  
  Quản lý và xem thông báo nội bộ, tích hợp xem lịch các sự kiện, cuộc họp,...

- **Tích hợp gửi Email & SMS tự động:**  
  Sử dụng JavaMail API và (ví dụ) Twilio để gửi thông báo qua Email/SMS khi có thay đổi trạng thái yêu cầu nghỉ, thông báo mới,...

## Cấu trúc dự án

```
quanlynhansu/
├── pom.xml
└── src/
    └── main/
        └── java/
            └── com/
                └── company/
                    └── quanlynhansu/
                        ├── dao/
                        │   ├── EmployeeDAO.java
                        │   ├── UserDAO.java
                        │   ├── TimekeepingDAO.java
                        │   ├── NotificationDAO.java
                        │   ├── CalendarEventDAO.java
                        │   ├── LeaveRequestDAO.java
                        │   ├── PerformanceEvaluationDAO.java
                        │   └── TrainingRecordDAO.java
                        │
                        ├── model/
                        │   ├── Employee.java
                        │   ├── User.java
                        │   ├── Timekeeping.java
                        │   ├── Notification.java
                        │   ├── CalendarEvent.java
                        │   ├── LeaveRequest.java
                        │   ├── PerformanceEvaluation.java
                        │   └── TrainingRecord.java
                        │
                        ├── util/
                        │   ├── DatabaseUtil.java
                        │   ├── EmailUtil.java
                        │   └── SMSUtil.java
                        │
                        ├── controller/
                        │   ├── EmployeeController.java
                        │   ├── UserController.java
                        │   ├── LeaveRequestController.java
                        │   ├── NotificationController.java
                        │   ├── CalendarEventController.java
                        │   ├── PerformanceEvaluationController.java
                        │   └── TrainingRecordController.java
                        │
                        └── view/
                            ├── admin/
                            │   ├── AdminDashboardFrame.java
                            │   ├── EmployeeManagementPanel.java
                            │   ├── UserManagementPanel.java
                            │   ├── LeaveManagementPanel.java
                            │   └── NotificationCalendarPanel.java
                            │
                            ├── hr/
                            │   └── PerformanceAndTrainingPanel.java
                            │
                            ├── internal/
                            │   ├── InternalNotificationPanel.java
                            │   └── CalendarPanel.java
                            │
                            └── reports/
                                ├── HRTimekeepingReportFrame.java
                                └── ReportStatisticsFrame.java
```

## Hướng dẫn cài đặt và chạy dự án

### Yêu cầu:
- **Java 8** trở lên.
- **Maven** để build và quản lý dependency.
- **PostgreSQL** (hoặc MySQL) – cập nhật thông tin kết nối trong `DatabaseUtil.java`.

### Các bước cài đặt:

1. **Clone dự án từ GitHub:**
   ```bash
   git clone <repository_url>
   cd quanlynhansu
   ```

2. **Tạo Database:**
   - Tạo cơ sở dữ liệu và thiết lập các bảng dựa trên các model của dự án (như `employees`, `users`, `timekeeping`, `notifications`, `calendar_event`, `leave_request`,…).
   - Bạn có thể sử dụng script SQL kèm theo (nếu có) hoặc tự tạo bảng theo định nghĩa trong các lớp model.

3. **Build dự án:**
   ```bash
   mvn clean install
   ```

4. **Chạy ứng dụng:**
   - Để chạy giao diện Dashboard Admin, sử dụng Maven Exec Plugin.
   ```bash
   mvn exec:java -Dexec.mainClass="com.company.quanlynhansu.view.admin.AdminDashboardFrame"
   ```

## Hướng dẫn sử dụng

- **Dashboard Admin:**  
  Giao diện chính với các tab:
  - **Quản lý nhân sự:** Thực hiện các thao tác CRUD (Thêm, Sửa, Xóa, Import dữ liệu) cho nhân viên.
  - **Quản lý người dùng:** Quản lý tài khoản người dùng cùng phân quyền (UserManagementPanel).
  - **Báo cáo thống kê & Chấm công:** Hiển thị dữ liệu báo cáo từ các module thống kê và chấm công.
  - **Hiệu suất & Đào tạo:** Quản lý đánh giá hiệu suất và các khóa đào tạo của nhân viên.
  - **Thông báo & Lịch:** Xem các thông báo nội bộ và lịch các sự kiện.
  - **Quản lý nghỉ phép:** Xem và duyệt yêu cầu nghỉ của nhân viên.

- **Tính năng gửi thông báo:**  
  Khi có hành động như duyệt đơn nghỉ hoặc tạo thông báo mới, hệ thống sẽ gọi các tiện ích `EmailUtil` và `SMSUtil` để gửi thông báo qua Email và SMS (cần cấu hình thông số SMTP / API SMS trước).

## Ghi chú

- **Cấu hình Database:**  
  Cập nhật URL, username và password của cơ sở dữ liệu trong `DatabaseUtil.java`.

- **Bảo mật:**  
  Trong thực tế, lưu mật khẩu cần phải mã hoá. Hiện tại chỉ dùng dưới dạng ví dụ.

- **Mở rộng:**  
  Dự án có thể được mở rộng thêm các tính năng như quản lý hợp đồng, audit logs, và tích hợp API bên ngoài cho email/SMS.

## Liên hệ

Nếu có bất kỳ thắc mắc hoặc góp ý nào, vui lòng liên hệ với duong.04@outlook.com hoặc mở issue trên GitHub.

---
