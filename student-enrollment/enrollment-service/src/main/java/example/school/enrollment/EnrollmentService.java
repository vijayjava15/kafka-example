package example.school.enrollment;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.stereotype.Service;

@Service
public class EnrollmentService {
    private final ConcurrentMap<UUID, Enrollment> enrollments = new ConcurrentHashMap<>();

    Enrollment enroll(EnrollmentRequest request) {
        Enrollment enrollment = new Enrollment(UUID.randomUUID(), request.studentId().strip(),
                request.courseName().strip(), request.email(), request.studentName(), Instant.now());
        return enrollments.computeIfAbsent(enrollment.enrollmentId(), id -> enrollment);
    }

    Optional<Enrollment> get(UUID id) {
        return Optional.ofNullable(enrollments.get(id));
    }

    List<Enrollment> list() {
        return enrollments.values().stream()
                .sorted(Comparator.comparing(Enrollment::enrolledAt))
                .toList();
    }
}
