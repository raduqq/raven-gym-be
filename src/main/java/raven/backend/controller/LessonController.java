package raven.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raven.backend.dto.LessonDto;
import raven.backend.service.LessonService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class LessonController {
    @Autowired
    private LessonService lessonService;

    @PostMapping("/lessons")
    public ResponseEntity<Object> create(@RequestBody LessonDto dto) {
        try {
            LessonDto createdDto = lessonService.create(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdDto);
        } catch (Exception e) {
            String eMessage = e.getMessage();

            if (eMessage != null && eMessage.contains("unique") && eMessage.contains("constraint")) {
                String errMessage = "A lesson with the given name already exists";
                return ResponseEntity.status(HttpStatus.CONFLICT).body(errMessage);
            } else {
                System.out.println(e.getMessage());
                String errMessage = "Internal error creating lesson";
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errMessage);
            }
        }
    }

    @GetMapping("/lessons")
    public List<LessonDto> getAll() {
        return lessonService.getAll();
    }

    @PutMapping("/lessons/{id}")
    public ResponseEntity<Object> update(@RequestBody LessonDto dto,
                                         @PathVariable("id") Integer id) {
        try {
            LessonDto updatedDto = lessonService.update(dto, id);
            return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/lessons/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable("id") Integer id) {
        try {
            lessonService.deleteById(id);

            String successMessage = "Deleted successfully";
            return ResponseEntity.status(HttpStatus.OK).body(successMessage);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/lessons/{lessonId}/coach/{coachId}")
    public ResponseEntity<Object> updateLessonsCoach(@PathVariable("lessonId") Integer lessonId,
                                                     @PathVariable("coachId") Integer coachId) {
        try {
            LessonDto updatedDto = lessonService.updateLessonCoach(lessonId, coachId);
            return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
