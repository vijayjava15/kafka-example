package example.school.enrollment;

import java.time.Instant;
import java.util.UUID;

public record Enrollment(UUID enrollmentId, String studentId, String courseName,
                         String email, String studentName, Instant enrolledAt) {
}
