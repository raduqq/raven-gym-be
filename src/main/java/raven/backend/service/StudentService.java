package raven.backend.service;

import raven.backend.dto.StudentDto;

import java.util.List;

public interface StudentService {
    StudentDto create(StudentDto studentDto);
    List<StudentDto> getAll();
    StudentDto update(StudentDto studentDto, Integer studentId);
    void deleteById(Integer studentId);
}
