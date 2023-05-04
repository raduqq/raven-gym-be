package raven.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raven.backend.dto.RegistrationDto;
import raven.backend.entity.Lesson;
import raven.backend.entity.Registration;
import raven.backend.entity.Student;
import raven.backend.mapper.RegistrationMapper;
import raven.backend.repository.*;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    @Autowired
    private RegistrationRepository registrationRepository;
    
    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private LessonRepository lessonRepository;
    
    @Override
    public RegistrationDto register(Integer studentId, Integer lessonId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchElementException("No student found with ID " + studentId));
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new NoSuchElementException("No lesson found with ID " + lessonId));

        Registration registration = new Registration();
        registration.setStudent(student);
        registration.setLesson(lesson);

        student.getLessons().add(lesson);
        lesson.getStudents().add(student);

        Registration savedRegistration = registrationRepository.save(registration);
        studentRepository.save(student);
        lessonRepository.save(lesson);

        return RegistrationMapper.INSTANCE.toRegistrationDto(savedRegistration);
    }

    @Override
    public List<RegistrationDto> getAll() {
        return registrationRepository.findAll()
                .stream()
                .map(RegistrationMapper.INSTANCE::toRegistrationDto)
                .collect(Collectors.toList());
    }
    
    @Override
    public void deregister(Integer studentId, Integer lessonId) {
        Registration registration = registrationRepository.findByStudentIdAndLessonId(studentId, lessonId)
                .orElseThrow(() -> new NoSuchElementException("No registration found with student ID " + studentId + " and lesson ID " + lessonId));

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchElementException("No student found with ID " + studentId));
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new NoSuchElementException("No lesson found with ID " + lessonId));

        student.getLessons().remove(lesson);
        lesson.getStudents().remove(student);

        registrationRepository.delete(registration);
    }
}