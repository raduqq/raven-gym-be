package raven.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raven.backend.dto.StudentDto;
import raven.backend.entity.Student;
import raven.backend.mapper.StudentMapper;
import raven.backend.repository.StudentRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public StudentDto create(StudentDto studentDto) {
        Student student = StudentMapper.INSTANCE.toStudent(studentDto);
        Student savedStudent = studentRepository.save(student);

        return StudentMapper.INSTANCE.toStudentDto(savedStudent);
    }

    @Override
    public List<StudentDto> getAll() {

        return studentRepository.findAll()
                .stream()
                .map(StudentMapper.INSTANCE::toStudentDto)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDto update(StudentDto studentDto, Integer studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchElementException("No student found with ID " + studentId));

        if (Objects.nonNull(studentDto.name()) && !"".equalsIgnoreCase(studentDto.name())) {
            student.setName(studentDto.name());
        }

        if (Objects.nonNull(studentDto.email()) && !"".equalsIgnoreCase(studentDto.email())) {
            student.setEmail(studentDto.email());
        }

        Student savedStudent = studentRepository.save(student);
        return StudentMapper.INSTANCE.toStudentDto(savedStudent);
    }

    @Override
    public void deleteById(Integer studentId) {
        studentRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchElementException("No student found with ID " + studentId));

        studentRepository.deleteById(studentId);
    }
}
