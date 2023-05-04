package raven.backend.dto;

import raven.backend.entity.Lesson;
import raven.backend.entity.Student;

public record RegistrationDto(
     Integer id,
     Student student,
     Lesson lesson
) {
}
