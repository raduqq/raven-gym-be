package raven.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raven.backend.entity.Registration;

import java.util.Optional;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Integer> {
    Optional<Registration> findByStudentIdAndLessonId(Integer studentId, Integer lessonId);
}
