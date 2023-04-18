package raven.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import raven.backend.dto.DisciplineDto;
import raven.backend.service.DisciplineService;

import java.util.List;

@RestController
public class DisciplineController {
    @Autowired
    private DisciplineService disciplineService;

    @PostMapping("/disciplines")
    public DisciplineDto saveDiscipline(
            @RequestBody DisciplineDto disciplineDto)
    {
        return disciplineService.saveDiscipline(disciplineDto);
    }

    @GetMapping("/disciplines")
    public List<DisciplineDto> getDisciplines()
    {
        return disciplineService.getDisciplines();
    }

    @PutMapping("/disciplines/{id}")
    public DisciplineDto updateDiscipline(@RequestBody DisciplineDto disciplineDto,
                                       @PathVariable("id") Integer disciplineId)
    {
        return disciplineService.updateDiscipline(disciplineDto, disciplineId);
    }

    @DeleteMapping("/disciplines/{id}")
    public String deleteDisciplineById(@PathVariable("id")
                                       Integer disciplineId)
    {
        disciplineService.deleteDisciplineById(disciplineId);

        return "Deleted Successfully";
    }
}
