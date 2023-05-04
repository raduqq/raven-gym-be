package raven.backend.dto;

import raven.backend.entity.Lesson;

import java.util.Set;

public record DisciplineDto(
        Integer id,
        String name,
        Set<Lesson> lessons
) {
}
