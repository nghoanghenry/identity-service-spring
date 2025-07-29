# Identity Service

Dịch vụ xác thực và quản lý người dùng được xây dựng bằng Spring Boot, cung cấp các API để quản lý người dùng, vai trò, quyền hạn và xác thực JWT.

## 🚀 Tính năng

- **Quản lý người dùng**: Tạo, cập nhật, xóa và xem thông tin người dùng
- **Xác thực JWT**: Đăng nhập và xác thực bằng JSON Web Token
- **Quản lý vai trò và quyền**: Hệ thống phân quyền dựa trên vai trò (RBAC)
- **Bảo mật**: Mã hóa mật khẩu và bảo vệ API endpoints
- **Token làm mới**: Hỗ trợ refresh token để duy trì phiên đăng nhập

## 🏗️ Kiến trúc

```
src/
├── main/
│   ├── java/com/nmhoang/identity_service/
│   │   ├── configuration/     # Cấu hình bảo mật và JWT
│   │   ├── controller/        # REST API Controllers
│   │   ├── dto/              # Data Transfer Objects
│   │   │   ├── request/      # Request DTOs
│   │   │   └── response/     # Response DTOs
│   │   ├── entity/           # JPA Entities
│   │   ├── enums/            # Enumerations
│   │   ├── exception/        # Exception handling
│   │   ├── mapper/           # MapStruct mappers
│   │   ├── repository/       # JPA Repositories
│   │   ├── service/          # Business logic
│   │   └── validator/        # Custom validators
│   └── resources/
│       └── application.properties
└── test/                     # Unit tests
```

## 🛠️ Công nghệ sử dụng

- **Java 21**: Ngôn ngữ lập trình
- **Spring Boot 3.5.0**: Framework chính
- **Spring Security**: Bảo mật và xác thực
- **Spring Data JPA**: Truy cập dữ liệu
- **PostgreSQL**: Cơ sở dữ liệu
- **JWT**: JSON Web Token cho xác thực
- **Lombok**: Giảm boilerplate code
- **MapStruct**: Object mapping
- **Maven**: Quản lý dependencies

## 📋 Yêu cầu hệ thống

- Java 21 hoặc cao hơn
- Maven 3.6+
- PostgreSQL 12+

## 🚀 Hướng dẫn cài đặt

### 1. Clone repository

```bash
git clone https://github.com/nghoanghenry/identity-service-spring.git
cd identity-service
```

### 2. Cấu hình cơ sở dữ liệu

Tạo database PostgreSQL:

```sql
CREATE DATABASE mydb;
```

Cập nhật thông tin kết nối trong `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/mydb
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 3. Chạy ứng dụng

#### Sử dụng Maven:

```bash
# Windows
.\mvnw.cmd spring-boot:run

# Linux/macOS
./mvnw spring-boot:run
```

#### Hoặc build và chạy JAR:

```bash
# Windows
.\mvnw.cmd clean package
java -jar target\identity-service-0.0.1-SNAPSHOT.jar

# Linux/macOS
./mvnw clean package
java -jar target/identity-service-0.0.1-SNAPSHOT.jar
```

Ứng dụng sẽ chạy tại: `http://localhost:8080`

## 📚 API Documentation

### Authentication APIs

| Method | Endpoint           | Mô tả          |
| ------ | ------------------ | -------------- |
| POST   | `/auth/login`      | Đăng nhập      |
| POST   | `/auth/introspect` | Kiểm tra token |
| POST   | `/auth/logout`     | Đăng xuất      |
| POST   | `/auth/refresh`    | Làm mới token  |

### User Management APIs

| Method | Endpoint          | Mô tả                    |
| ------ | ----------------- | ------------------------ |
| POST   | `/users`          | Tạo người dùng mới       |
| GET    | `/users`          | Lấy danh sách người dùng |
| GET    | `/users/{userId}` | Lấy thông tin người dùng |
| PUT    | `/users/{userId}` | Cập nhật người dùng      |
| DELETE | `/users/{userId}` | Xóa người dùng           |
| GET    | `/users/myInfo`   | Lấy thông tin của tôi    |

### Role Management APIs

| Method | Endpoint        | Mô tả                 |
| ------ | --------------- | --------------------- |
| POST   | `/roles`        | Tạo vai trò mới       |
| GET    | `/roles`        | Lấy danh sách vai trò |
| DELETE | `/roles/{role}` | Xóa vai trò           |

### Permission Management APIs

| Method | Endpoint                    | Mô tả               |
| ------ | --------------------------- | ------------------- |
| POST   | `/permissions`              | Tạo quyền mới       |
| GET    | `/permissions`              | Lấy danh sách quyền |
| DELETE | `/permissions/{permission}` | Xóa quyền           |

## 🔐 Cấu hình JWT

Cấu hình JWT trong `application.properties`:

```properties
jwt.signerKey=your_secret_key_here
jwt.valid-duration=10          # Thời gian hiệu lực token (phút)
jwt.refreshable-duration=120   # Thời gian có thể refresh (phút)
```

## 📝 Ví dụ sử dụng

### 1. Tạo người dùng mới

```bash
curl -X POST http://localhost:8080/users \
  -H "Content-Type: application/json" \
  -d '{
    "username": "john_doe",
    "password": "password123",
    "firstName": "John",
    "lastName": "Doe",
    "dob": "1990-01-01"
  }'
```

### 2. Đăng nhập

```bash
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "john_doe",
    "password": "password123"
  }'
```

### 3. Truy cập API được bảo vệ

```bash
curl -X GET http://localhost:8080/users/myInfo \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

## 🧪 Chạy Tests

```bash
# Windows
.\mvnw.cmd test

# Linux/macOS
./mvnw test
```

## 📁 Cấu trúc dự án chi tiết

- **Controllers**: Xử lý HTTP requests và responses
- **Services**: Logic nghiệp vụ chính
- **Repositories**: Truy cập dữ liệu
- **Entities**: Mô hình dữ liệu JPA
- **DTOs**: Đối tượng truyền dữ liệu
- **Mappers**: Chuyển đổi giữa entities và DTOs
- **Configuration**: Cấu hình Spring Security và JWT
- **Exception**: Xử lý ngoại lệ toàn cục

## 🔧 Cấu hình môi trường

### Development

```properties
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
```

### Production

```properties
spring.jpa.show-sql=false
spring.jpa.hibernate.ddl-auto=validate
```

## 🤝 Đóng góp

1. Fork dự án
2. Tạo branch cho feature (`git checkout -b feature/AmazingFeature`)
3. Commit changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to branch (`git push origin feature/AmazingFeature`)
5. Tạo Pull Request

## 📄 License

Dự án này được phát hành dưới giấy phép MIT. Xem file `LICENSE` để biết thêm chi tiết.

## 👨‍💻 Tác giả

- **Nguyễn Minh Hoàng** - [nghoanghenry](https://github.com/nghoanghenry)

## 📞 Liên hệ

Nếu có câu hỏi hoặc góp ý, vui lòng tạo issue trên GitHub hoặc liên hệ qua email.

---

⭐ Nếu dự án này hữu ích, hãy cho một star để ủng hộ!
