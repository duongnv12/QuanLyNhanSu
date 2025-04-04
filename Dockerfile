# Stage 1: Build ứng dụng
FROM maven:3.8.6-openjdk-11 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn package -DskipTests

# Stage 2: Chạy ứng dụng
FROM openjdk:11-jre-slim
WORKDIR /app
# Cần đảm bảo tên file jar chính xác được copy, ví dụ:
COPY --from=build /app/target/QuanLyNhanSu-1.0-SNAPSHOT.jar QuanLyNhanSu.jar
EXPOSE 8080
CMD ["java", "-jar", "QuanLyNhanSu.jar"]
