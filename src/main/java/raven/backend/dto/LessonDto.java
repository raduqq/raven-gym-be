package raven.backend.dto;

import raven.backend.entity.Coach;
import raven.backend.entity.Discipline;

public record LessonDto(
        Integer id,
        String name,
        Coach coach,
        Discipline discipline
) {
}
