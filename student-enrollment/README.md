# Student enrollment API

The enrollment service accepts student details over HTTP and stores enrollments in memory. The email-service module is a Spring Boot scaffold with SMTP settings for future integration.

## Run the demo

Install Docker with the Compose plugin, open a terminal in this directory, and run:

```powershell
docker compose up --build
```

Wait for the enrollment service to start, then run these commands in another PowerShell window:

```powershell
$body = @{
    studentId = 'S-1001'
    courseName = 'Introduction to Java'
    email = 'student@example.test'
    studentName = 'Asha'
} | ConvertTo-Json

$enrollment = Invoke-RestMethod -Method Post -Uri 'http://localhost:8080/enrollments' -ContentType 'application/json' -Body $body
$enrollment
Invoke-RestMethod "http://localhost:8080/enrollments/$($enrollment.enrollmentId)"
Invoke-RestMethod 'http://localhost:8080/enrollments'
```

Stop the demo with `docker compose down`.

## API

| Method | Path | Result |
| --- | --- | --- |
| POST | `/enrollments` | Creates a record and returns `201 Created` with its ID and a `Location` header. |
| GET | `/enrollments` | Lists the current instance's enrollments in creation order. |
| GET | `/enrollments/{id}` | Retrieves an enrollment by its generated UUID, or returns `404`. |

The POST requires `studentId`, `courseName`, and `email` as strings. `studentName` is optional. Invalid input returns `400`.
Enrollments are cleared when the service restarts. Each POST creates a separate enrollment.

## Run with Maven

With Java 21 and Maven installed, run:

```powershell
mvn clean package
java -jar enrollment-service/target/enrollment-service.jar
```
