package raven.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raven.backend.dto.CoachDto;
import raven.backend.service.CoachService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class CoachController {
    @Autowired
    private CoachService coachService;

    @PostMapping("/coaches")
    public ResponseEntity<Object> create(@RequestBody CoachDto coachDto) {
        try {
            CoachDto createdCoachDto = coachService.create(coachDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCoachDto);
        } catch (Exception e) {
            String eMessage = e.getMessage();

            if (eMessage != null && eMessage.contains("unique") && eMessage.contains("constraint")) {
                String errMessage = "A coach with the given name already exists";
                return ResponseEntity.status(HttpStatus.CONFLICT).body(errMessage);
            } else {
                System.out.println(e.getMessage());
                String errMessage = "Internal error creating coach";
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errMessage);
            }
        }
    }

    @GetMapping("/coaches")
    public List<CoachDto> getAll() {
        return coachService.getAll();
    }

    @PutMapping("/coaches/{id}")
    public ResponseEntity<Object> update(@RequestBody CoachDto coachDto,
                                         @PathVariable("id") Integer coachId) {
        try {
            CoachDto updatedCoachDto = coachService.update(coachDto, coachId);
            return ResponseEntity.status(HttpStatus.OK).body(updatedCoachDto);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/coaches/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable("id") Integer coachId) {
        try {
            coachService.deleteById(coachId);

            String successMessage = "Deleted successfully";
            return ResponseEntity.status(HttpStatus.OK).body(successMessage);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
