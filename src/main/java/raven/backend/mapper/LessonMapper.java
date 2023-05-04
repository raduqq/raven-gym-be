package raven.backend.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import raven.backend.dto.LessonDto;
import raven.backend.entity.Lesson;

@Mapper
public interface LessonMapper {
    LessonMapper INSTANCE = Mappers.getMapper(LessonMapper.class);

    LessonDto toLessonDto(Lesson lesson);

    @InheritInverseConfiguration
    Lesson toLesson(LessonDto lessonDto);
}
