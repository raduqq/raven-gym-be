package raven.backend.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import raven.backend.dto.StudentDto;
import raven.backend.entity.Student;

@Mapper
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    StudentDto toStudentDto(Student student);

    @InheritInverseConfiguration
    Student toStudent(StudentDto studentDto);
}
