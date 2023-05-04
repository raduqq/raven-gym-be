package raven.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raven.backend.dto.RegistrationDto;
import raven.backend.service.RegistrationService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;

    // Doesn't exactly work; creates 4 instead of 1 entry
    @PostMapping("/registrations/student/{studentId}/lesson/{lessonId}")
    public ResponseEntity<Object> create(@PathVariable("studentId") Integer studentId,
                                         @PathVariable("lessonId") Integer lessonId) {
        try {
            RegistrationDto createdDto = registrationService.register(studentId, lessonId);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdDto);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/registrations")
    public List<RegistrationDto> getAll() {
        return registrationService.getAll();
    }

    @DeleteMapping("/registrations/student/{studentId}/lesson/{lessonId}")
    public ResponseEntity<Object> delete(@PathVariable("studentId") Integer studentId,
                                             @PathVariable("lessonId") Integer lessonId) {
        try {
            registrationService.deregister(studentId, lessonId);

            String successMessage = "Deleted successfully";
            return ResponseEntity.status(HttpStatus.OK).body(successMessage);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
