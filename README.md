# 🚀 CodeArena – Coding Platform Backend

A backend system for a coding practice platform (similar to LeetCode) built using **Spring Boot**, **MySQL**, and **JWT Authentication**.
Supports problem management, secure user authentication, and real-time code execution.

---

## 🧠 Features

* 🔐 JWT-based Authentication & Authorization (USER / ADMIN)
* 📦 REST APIs for Problems and Submissions
* 🧩 Problem Management (Create, Update, Delete – Admin only)
* 🧪 Code Submission System (multi-language support)
* ⚡ Real-time Code Execution using Judge0 API
* 🧱 Clean Architecture (Controller → Service → Repository)
* 🛡️ Secure API Design using DTOs (no entity exposure)

---

## 🛠️ Tech Stack

* **Backend:** Java, Spring Boot
* **Security:** Spring Security, JWT
* **Database:** MySQL, JPA, Hibernate
* **API Testing:** Postman
* **Build Tool:** Maven

---

## ⚙️ Project Structure

```
com.codearena
│
├── controller
├── service
├── repository
├── entity
├── dto
├── security
├── config
```

---

## 🔑 API Endpoints

### Auth

* `POST /auth/register`
* `POST /auth/login`

### Problems

* `GET /api/problems`
* `POST /admin/problems`
* `PUT /admin/problems/{id}`
* `DELETE /admin/problems/{id}`

### Submissions

* `POST /api/submissions/{problemId}`

---

## ⚡ Code Execution Flow

1. User submits code
2. Backend sends request to Judge0 API
3. Judge0 returns execution token
4. Backend polls result
5. Final status returned:

   * ACCEPTED
   * ERROR / FAILED

---

## 🧪 Sample Request

```json
POST /api/submissions/1

{
  "code": "print(\"hello\")",
  "language": "python"
}
```

---

## 📦 Setup Instructions

### 1. Clone Repository

```bash
git clone https://github.com/your-username/codearena.git
cd codearena
```

### 2. Configure Database

Update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/codearena_db
spring.datasource.username=root
spring.datasource.password=your_password
```

### 3. Add Judge0 API Key

```properties
judge0.api.url=https://judge0-ce.p.rapidapi.com
judge0.api.key=YOUR_API_KEY
```

### 4. Run Project

```bash
mvn spring-boot:run
```

---

## 📌 Future Improvements

* Add test case validation (input/output matching)
* Leaderboard system
* Pagination & filtering
* Docker deployment
* Rate limiting

---

## 👨‍💻 Author

**Ankur**
🔗 GitHub: https://github.com/ankurjangra-1
🔗 LinkedIn: https://www.linkedin.com/in/ankurjangra

---

## ⭐ Note

This project demonstrates backend system design, API security, and real-world coding platform architecture.
