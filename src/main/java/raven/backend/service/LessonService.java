package raven.backend.service;

import raven.backend.dto.LessonDto;

import java.util.List;

public interface LessonService {
    LessonDto create(LessonDto lessonDto);

    List<LessonDto> getAll();

    LessonDto update(LessonDto lessonDto, Integer lessonId);

    void deleteById(Integer lessonId);

    LessonDto updateLessonCoach(Integer lessonId, Integer coachId);
    LessonDto updateLessonDiscipline(Integer lessonId, Integer disciplineId);
}
