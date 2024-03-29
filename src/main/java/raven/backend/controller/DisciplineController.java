package raven.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raven.backend.dto.DisciplineDto;
import raven.backend.service.DisciplineService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class DisciplineController {
    @Autowired
    private DisciplineService disciplineService;

    @PostMapping("/disciplines")
    public ResponseEntity<Object> create(@RequestBody DisciplineDto dto) {
        try {
            DisciplineDto createdDto = disciplineService.create(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdDto);
        } catch (Exception e) {
            String eMessage = e.getMessage();

            if (eMessage != null && eMessage.contains("unique") && eMessage.contains("constraint")) {
                String errMessage = "A discipline with the given name already exists";
                return ResponseEntity.status(HttpStatus.CONFLICT).body(errMessage);
            } else {
                String errMessage = "Internal error creating discipline";
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errMessage);
            }
        }
    }

    @GetMapping("/disciplines")
    public List<DisciplineDto> getAll() {
        return disciplineService.getAll();
    }

    @PutMapping("/disciplines/{id}")
    public ResponseEntity<Object> update(@RequestBody DisciplineDto dto,
                                         @PathVariable("id") Integer id) {
        try {
            DisciplineDto updatedDto = disciplineService.update(dto, id);
            return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/disciplines/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable("id") Integer id) {
        try {
            disciplineService.deleteById(id);

            String successMessage = "Deleted successfully";
            return ResponseEntity.status(HttpStatus.OK).body(successMessage);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
