package raven.backend.dto;

import raven.backend.entity.Coach;

public record LessonDto(
        Integer id,
        String name,
        Coach coach
) {
}
