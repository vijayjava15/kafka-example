package example.school.enrollment;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {
    private final EnrollmentService service;

    public EnrollmentController(EnrollmentService service) {
        this.service = service;
    }

    @PostMapping
    ResponseEntity<Enrollment> enroll(@Valid @RequestBody EnrollmentRequest request) {
        Enrollment enrollment = service.enroll(request);
        return ResponseEntity.created(URI.create("/enrollments/" + enrollment.enrollmentId()))
                .body(enrollment);
    }

    @GetMapping
    List<Enrollment> list() {
        return service.list();
    }

    @GetMapping("/{id}")
    ResponseEntity<Enrollment> get(@PathVariable UUID id) {
        return ResponseEntity.of(service.get(id));
    }
}

record EnrollmentRequest(
        @NotBlank @Size(max = 100) String studentId,
        @NotBlank @Size(max = 200) String courseName,
        @NotBlank @Email @Size(max = 254) String email,
        @Size(max = 200) String studentName) {
}
