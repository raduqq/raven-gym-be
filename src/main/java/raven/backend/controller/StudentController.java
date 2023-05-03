package raven.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raven.backend.dto.StudentDto;
import raven.backend.service.StudentService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/students")
    public ResponseEntity<Object> create(
            @RequestBody StudentDto studentDto)
    {
        try {
            StudentDto createdStudentDto = studentService.create(studentDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdStudentDto);
        } catch (Exception e) {
            String eMessage = e.getMessage();

            if (eMessage != null && eMessage.contains("unique") && eMessage.contains("constraint")) {
                String errMessage = "A student with the given name already exists";
                return ResponseEntity.status(HttpStatus.CONFLICT).body(errMessage);
            } else {
                System.out.println(e.getMessage());
                String errMessage = "Internal error creating student";
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errMessage);
            }
        }
    }

    @GetMapping("/students")
    public List<StudentDto> getAll()
    {
        return studentService.getAll();
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<Object> update(@RequestBody StudentDto studentDto,
                                         @PathVariable("id") Integer studentId)
    {
        try {
            StudentDto updatedStudentDto = studentService.update(studentDto, studentId);
            return ResponseEntity.status(HttpStatus.OK).body(updatedStudentDto);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable("id")
                                             Integer studentId)
    {
        try {
            studentService.deleteById(studentId);

            String successMessage = "Deleted successfully";
            return ResponseEntity.status(HttpStatus.OK).body(successMessage);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
