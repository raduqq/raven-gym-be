package raven.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raven.backend.entity.Lesson;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer> {
}
