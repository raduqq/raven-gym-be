package raven.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raven.backend.dto.LessonDto;
import raven.backend.entity.Coach;
import raven.backend.entity.Lesson;
import raven.backend.mapper.LessonMapper;
import raven.backend.repository.CoachRepository;
import raven.backend.repository.LessonRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class LessonServiceImpl implements LessonService {
    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private CoachRepository coachRepository;

    @Override
    public LessonDto create(LessonDto lessonDto) {
        Lesson lesson = LessonMapper.INSTANCE.toLesson(lessonDto);
        Lesson savedLesson = lessonRepository.save(lesson);

        return LessonMapper.INSTANCE.toLessonDto(savedLesson);
    }

    @Override
    public List<LessonDto> getAll() {

        return lessonRepository.findAll()
                .stream()
                .map(LessonMapper.INSTANCE::toLessonDto)
                .collect(Collectors.toList());
    }

    @Override
    public LessonDto update(LessonDto lessonDto, Integer lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new NoSuchElementException("No lesson found with ID " + lessonId));

        lesson.setName(lessonDto.name());

        Lesson savedLesson = lessonRepository.save(lesson);
        return LessonMapper.INSTANCE.toLessonDto(savedLesson);
    }

    @Override
    public void deleteById(Integer lessonId) {
        lessonRepository.findById(lessonId)
                .orElseThrow(() -> new NoSuchElementException("No lesson found with ID " + lessonId));

        lessonRepository.deleteById(lessonId);
    }

    @Override
    public LessonDto updateLessonCoach(Integer lessonId, Integer coachId) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new NoSuchElementException("No lesson found with ID " + lessonId));
        Coach newCoach = coachRepository.findById(coachId)
                .orElseThrow(() -> new NoSuchElementException("No coach found with ID " + coachId));

        Coach currCoach = lesson.getCoach();
        if (Objects.nonNull(currCoach)){
            currCoach.getLessons().remove(lesson);
        }

        lesson.setCoach(newCoach);
        newCoach.getLessons().add(lesson);

        Lesson savedLesson = lessonRepository.save(lesson);
        coachRepository.save(newCoach);

        return LessonMapper.INSTANCE.toLessonDto(savedLesson);
    }
}
